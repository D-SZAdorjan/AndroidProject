package com.example.bazar.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import com.example.bazar.R
import com.example.bazar.databinding.FragmentProfileBinding
import com.example.bazar.databinding.ProfileViewOthersBinding

class ProfileFragment : Fragment() {

    private var _binding : FragmentProfileBinding? = null
    private var _binding2 : ProfileViewOthersBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!
    private val binding2 get() = _binding2!!

    private lateinit var myToolBar: Toolbar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root

        myToolBar = binding.fragTLTopToolbar.toolbar

        return view
    }

    private fun setupToolBar(){

        myToolBar.inflateMenu(R.menu.time_line_menu)

        myToolBar.findViewById<Toolbar>(R.id.toolBar_search).isVisible=false
        myToolBar.findViewById<Toolbar>(R.id.toolBar_filter).isVisible=false
        myToolBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.toolBar_search -> {
                    true
                }
                R.id.toolBar_filter -> {
                    true
                }
                R.id.toolBar_avatar -> {
                    true
                }
                else -> false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _binding2 = null
    }

}