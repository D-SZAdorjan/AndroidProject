package com.example.bazar.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.bazar.R
import com.example.bazar.databinding.FragmentBaseBinding
import com.example.bazar.databinding.FragmentLoginBinding

open class BaseFragment : Fragment() {

    private var _binding : FragmentBaseBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    lateinit var blackBckg : View
    lateinit var backBtn : ImageView
    lateinit var fragmentTitle : TextView
    lateinit var bazaarLogo : ImageView
    lateinit var defaultUserImage : ImageView
    lateinit var closeBtn : ImageView
    lateinit var searchBtn : ImageView
    lateinit var filterBtn : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBaseBinding.inflate(inflater, container, false)
        val view = binding.root

        /*blackBckg = binding.baseFragBlackbckg
        backBtn = binding.baseFragBackBtn
        fragmentTitle = binding.baseFragFragTitle
        bazaarLogo = binding.baseFragBazaarLogo
        defaultUserImage = binding.baseFragDefaultAvatar
        closeBtn = binding.baseFragCloseBtn
        searchBtn = binding.baseFragSearchBtn
        filterBtn = binding.baseFragFilterBtn

        blackBckgNeeded()
        backButtonNeeded()
        fragmentTitleNeeded()
        bazaarLogoNeeded()
        defaultAvatarNeeded()
        closeBtnNeeded()
        searchBtnNeeded()
        filterBtnNeeded()*/

        return view
    }

    /*open fun blackBckgNeeded(){
        blackBckg.visibility = View.GONE
    }

    open fun backButtonNeeded(){
        backBtn.visibility = View.GONE
    }

    open fun fragmentTitleNeeded(){
        fragmentTitle.visibility = View.GONE
    }

    open fun bazaarLogoNeeded(){
        bazaarLogo.visibility = View.GONE
    }

    open fun defaultAvatarNeeded(){
        defaultUserImage.visibility = View.GONE
    }

    open fun closeBtnNeeded(){
        closeBtn.visibility = View.GONE
    }

    open fun filterBtnNeeded(){
        filterBtn.visibility = View.GONE
    }

    open fun searchBtnNeeded(){
        searchBtn.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }*/
}