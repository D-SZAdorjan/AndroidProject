package com.example.bazar.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.get
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bazar.App
import com.example.bazar.DataAdapter
import com.example.bazar.R
import com.example.bazar.databinding.ApplicationBarBinding
import com.example.bazar.databinding.FragmentTimeLineBinding
import com.example.bazar.model.Product
import com.example.bazar.viewmodels.MainScreenViewModel

class TimeLineFragment : Fragment(), DataAdapter.OnItemClickListener, DataAdapter.OnItemLongClickListener {

    private var _binding : FragmentTimeLineBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private val mainScreenViewModel : MainScreenViewModel by activityViewModels()
    private lateinit var recView: RecyclerView
    private lateinit var adapter: DataAdapter

    private lateinit var myToolBar: Toolbar

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

        myToolBar = binding.fragTLTopToolbar.toolbar
        myToolBar.setLogo(R.drawable.ic_bazaar_logo_coloured)

        recView = binding.recyclerView
        setupRecyclerView()
        mainScreenViewModel.getProducts()
        mainScreenViewModel.products.observe(viewLifecycleOwner){
            adapter.setData(mainScreenViewModel.products.value as ArrayList<Product>)
            adapter.notifyDataSetChanged()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolBar(view)
    }

    private fun setupToolBar(view: View){
        myToolBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.toolBar_search -> {
                    true
                }
                R.id.toolBar_filter -> {
                    true
                }
                R.id.toolBar_avatar -> {
                    Navigation.findNavController(view).navigate(R.id.navigateFromTimeLineFragmentToProfileFragment)
                    true
                }
                else -> false
            }
        }

        myToolBar.inflateMenu(R.menu.time_line_menu)
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