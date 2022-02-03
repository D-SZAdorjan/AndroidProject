package com.example.bazar.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.bazar.App
import com.example.bazar.R
import com.example.bazar.databinding.FragmentProfileBinding
import com.example.bazar.model.Product
import com.example.bazar.model.User
import com.example.bazar.model.UserInfo
import com.example.bazar.repository.Repository
import com.example.bazar.viewmodels.ProfileFragmentViewModel
import com.example.bazar.viewmodels.ProfileFragmentViewModelFactory
import com.google.android.material.button.MaterialButton

class ProfileFragment : Fragment() {

    lateinit var userProfileImage : ImageView
    lateinit var userName : TextView
    lateinit var userEmail : TextView
    lateinit var userPhoneNumber : TextView
    lateinit var editProfileBtn : MaterialButton

    private var _binding : FragmentProfileBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private lateinit var myToolBar: Toolbar
    private lateinit var profFragmentViewModel : ProfileFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root

        myToolBar = binding.fragTLTopToolbar.toolbar
        val factory = ProfileFragmentViewModelFactory(requireContext(), Repository())
        profFragmentViewModel = ViewModelProvider(this, factory).get(ProfileFragmentViewModel::class.java)

        val currUser : String = this.requireArguments().getString("currUser")!!

        bindViews()

        setupToolBar(view)

        if( App.thisUser.username.compareTo( currUser ) == 0 ) {
            setupView(App.thisUser)
            editProfileBtn.setOnClickListener {
                onEditProfileBtnClick()
            }
        }else{
            profFragmentViewModel.getUserInformation(currUser)
            setupView(profFragmentViewModel.user.value)
        }

        return view
    }

    fun bindViews(){
        userProfileImage = binding.fragProfileProfilePic
        userName = binding.fragProfileUserName
        userEmail = binding.fragProfileEmail
        userPhoneNumber = binding.fragProfilePhoneNumber
        editProfileBtn = binding.fragProfileEditBtn
    }

    fun setupView(currUser : UserInfo?){
        userName.text = currUser!!.username
        userEmail.text = currUser!!.email
        userPhoneNumber.text = currUser!!.phone_number
    }

    fun onEditProfileBtnClick(){
        Navigation.findNavController(requireView()).navigate(R.id.navigateFromProfileFragmentToEditProfileFragment)
    }

    private fun setupToolBar(view: View){

        //beallítom a nem alapértelemezett visszafele gomb, hogy nézzen ki
        myToolBar.setNavigationIcon(R.drawable.ic_arrow_next_pagination)
        myToolBar.title = "Profile"
        myToolBar.setTitleTextColor(resources.getColor(R.color.white))

        //beteszem a my_market_menu kinezetet a toolbaromba
        myToolBar.inflateMenu(R.menu.profile_menu)

        //kereses és user icon menü itemek
        myToolBar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.toolBar_avatar -> {
                    val bundle = bundleOf("currUser" to App.thisUser.username)
                    Navigation.findNavController(view).navigate(R.id.navigateFrommyMarketFragmentToProfileFragment, bundle)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}