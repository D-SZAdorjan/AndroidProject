package com.example.bazar.fragments

import android.os.Bundle
import android.text.Layout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bazar.App
import com.example.bazar.DataAdapter
import com.example.bazar.R
import com.example.bazar.databinding.FragmentTimeLineBinding
import com.example.bazar.databinding.TopToolbarBinding
import com.example.bazar.model.Product
import com.example.bazar.viewmodels.MainScreenViewModel

class TimeLineFragment : Fragment(), DataAdapter.OnItemClickListener, DataAdapter.OnItemLongClickListener {

    private var _binding : FragmentTimeLineBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private val mainScreenViewModel : MainScreenViewModel by activityViewModels()
    private lateinit var recView: RecyclerView
    private lateinit var adapter: DataAdapter

    private lateinit var toolBar: View
    private lateinit var bazaarLogo: ImageView
    private lateinit var searchBtn: ImageView
    private lateinit var filterBtn: ImageView
    private lateinit var avatar: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTimeLineBinding.inflate(inflater, container, false)
        val view = binding.root

        setUpToolBar()

        recView = binding.recyclerView
        setupRecyclerView()
        mainScreenViewModel.getProducts()
        mainScreenViewModel.products.observe(viewLifecycleOwner){
            adapter.setData(mainScreenViewModel.products.value as ArrayList<Product>)
            adapter.notifyDataSetChanged()
        }
        return view
    }

    private fun setUpToolBar(){
        toolBar = requireActivity().findViewById(R.id.mainScreen_topToolBar)
        bazaarLogo = requireActivity().findViewById(R.id.mainScreen_bazaar_logo)
        searchBtn = requireActivity().findViewById(R.id.mainScreen_searchBtn)
        filterBtn = requireActivity().findViewById(R.id.mainScreen_filterBtn)
        avatar = requireActivity().findViewById(R.id.mainScreen_defaultAvatar)

        toolBar.visibility = View.VISIBLE
        bazaarLogo.visibility = View.VISIBLE
        searchBtn.visibility = View.VISIBLE
        filterBtn.visibility = View.VISIBLE
        avatar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView(){
        adapter = DataAdapter(ArrayList<Product>(), this.requireContext(), App.thisUser, this, this)
        recView.adapter = adapter
        recView.layoutManager = LinearLayoutManager(this.context)
        recView.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
        recView.setHasFixedSize(true)
    }

    override fun onItemClick(position: Int) {
//        TODO("Not yet implemented")
    }

    override fun onItemLongClick(position: Int) {
//        TODO("Not yet implemented")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}