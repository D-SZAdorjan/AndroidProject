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
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bazar.App
import com.example.bazar.DataAdapter
import com.example.bazar.MyMarketDataAdapter
import com.example.bazar.R
import com.example.bazar.databinding.FragmentMyMarketBinding
import com.example.bazar.databinding.FragmentTimeLineBinding
import com.example.bazar.model.Product
import com.example.bazar.repository.Repository
import com.example.bazar.viewmodels.*
import java.security.acl.Group

class MyMarketFragment : Fragment(), MyMarketDataAdapter.OnItemClickListener, MyMarketDataAdapter.OnItemLongClickListener {

    private var _binding : FragmentMyMarketBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private lateinit var myMarketViewModel : MyMarketViewModel
    private lateinit var recView: RecyclerView
    private lateinit var adapter: MyMarketDataAdapter

    private lateinit var myToolBar: Toolbar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMyMarketBinding.inflate(inflater, container, false)
        val view = binding.root

        myToolBar = binding.fragMMTopToolbar.toolbar

        val factory = MyMarketViewModelFactory(requireContext(), Repository())
        myMarketViewModel = ViewModelProvider(this, factory).get(MyMarketViewModel::class.java)

        recView = binding.mymarketFragRecyclerView
        setupRecyclerView()
        myMarketViewModel.getUserProducts()
        myMarketViewModel.products.observe(viewLifecycleOwner){
            adapter.setData(myMarketViewModel.products.value as ArrayList<Product>)
            adapter.notifyDataSetChanged()
        }
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolBar(view)
    }

    private fun setupToolBar(view: View){

        //beallítom a nem alapértelemezett visszafele gomb, hogy nézzen ki
        myToolBar.setNavigationIcon(R.drawable.ic_arrow_next_pagination)
        myToolBar.title = "My Market"
        myToolBar.setTitleTextColor(resources.getColor(R.color.white))

        //beteszem a my_market_menu kinezetet a toolbaromba
        myToolBar.inflateMenu(R.menu.my_market_menu)

        //kereses és user icon menü itemek
        myToolBar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.toolBar_search -> {
                    true
                }
                R.id.toolBar_avatar -> {
                    Navigation.findNavController(view).navigate(R.id.navigateFrommyMarketFragmentToProfileFragment)
                    true
                }
                else -> {
                    false
                }
            }
        }

        //nem alapértelemezett vissza gomb
        myToolBar.setNavigationOnClickListener{
            findNavController().navigateUp()
        }
    }

    private fun setupRecyclerView(){
        Log.d("myMarketFragxxx", "idaig eljut5!")
        adapter = MyMarketDataAdapter(ArrayList<Product>(), this.requireContext(), this, this)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(position: Int) {
        TODO("Not yet implemented")
    }

    override fun onItemLongClick(position: Int) {
        TODO("Not yet implemented")
    }
}