package com.example.bazar.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bazar.App
import com.example.bazar.DataAdapter
import com.example.bazar.R
import com.example.bazar.databinding.FragmentMyMarketBinding
import com.example.bazar.databinding.FragmentTimeLineBinding
import com.example.bazar.model.Product
import com.example.bazar.viewmodels.MainScreenViewModel
import java.security.acl.Group

class MyMarketFragment : Fragment(), DataAdapter.OnItemClickListener, DataAdapter.OnItemLongClickListener {

    private var _binding : FragmentMyMarketBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private val mainScreenViewModel : MainScreenViewModel by activityViewModels()
    private lateinit var recView: RecyclerView
    private lateinit var adapter: DataAdapter

    private lateinit var myToolBar: Toolbar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMyMarketBinding.inflate(inflater, container, false)
        val view = binding.root

        myToolBar = binding.fragMMTopToolbar.toolbar
        myToolBar.setNavigationIcon(R.drawable.ic_arrow_next_pagination)
        myToolBar.setNavigationOnClickListener {
            // back button pressed
            findNavController().navigateUp()
        }

        recView = binding.mymarketFragRecyclerView
        setupRecyclerView()
        //mainScreenViewModel.getProducts()
        //var list = mainScreenViewModel.products.value?.filter { it.username == "demen" }
        //Log.d("mymarketlist", App.thisUser.username + ", " + list.toString())
        mainScreenViewModel.products.value?.forEach{
            Log.d("mymarketlist", App.thisUser.username + ", " + it.username)
        }
        mainScreenViewModel.products.observe(viewLifecycleOwner){
            adapter.setData(mainScreenViewModel.products.value as ArrayList<Product>)
            adapter.notifyDataSetChanged()
        }
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolBar()
    }

    private fun setupToolBar(){

        myToolBar.inflateMenu(R.menu.time_line_menu)

        myToolBar.findViewById<Toolbar>(R.id.toolBar_filter).isVisible = false
        myToolBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.toolBar_search -> {
                    true
                }
                R.id.toolBar_avatar -> {
                    true
                }
                else -> false
            }
        }
    }

    private fun setupRecyclerView(){
        Log.d("myMarketFragxxx", "idaig eljut5!")
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

    /*private fun makeToolBarInvisible(){
        toolBar.visibility = View.GONE
        group.visibility = View.GONE
        searchBtn.visibility = View.GONE
        avatar.visibility = View.GONE
    }*/

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        //makeToolBarInvisible()
    }

    override fun onItemClick(position: Int) {
        TODO("Not yet implemented")
    }

    override fun onItemLongClick(position: Int) {
        TODO("Not yet implemented")
    }
}