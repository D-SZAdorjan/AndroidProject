package com.example.bazar.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.bazar.App
import com.example.bazar.R
import com.example.bazar.databinding.FragmentAddProductBinding
import com.example.bazar.databinding.FragmentEditProfileBinding
import com.example.bazar.repository.Repository
import com.example.bazar.viewmodels.AddProductViewModel
import com.example.bazar.viewmodels.AddProductViewModelFactory
import com.example.bazar.viewmodels.EditProfileViewModel
import com.example.bazar.viewmodels.EditProfileViewModelFactory
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.launch

class EditProfileFragment : Fragment() {

    private var _binding : FragmentEditProfileBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private lateinit var editProfViewModel : EditProfileViewModel
    private lateinit var emailField : EditText
    private lateinit var userNameField : EditText
    private lateinit var phoneNumberField : EditText
    private lateinit var submitBtn : MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = EditProfileViewModelFactory(this.requireContext(), Repository())
        editProfViewModel = ViewModelProvider(this, factory).get(EditProfileViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        val view = binding.root

        bindViews()

        submitBtn.setOnClickListener {
            onSubmitBtnClick()
        }
        return view
    }

    fun bindViews(){
        emailField = binding.fragEditProfileModifyEmail
        userNameField = binding.fragEditProfileModifyUserName
        phoneNumberField = binding.fragEditProfileModifyPhoneNumber
        submitBtn = binding.fragEditProfileSubmitBtn
    }

    fun onSubmitBtnClick(){
        lifecycleScope.launch {
            editProfViewModel.user.value.let {
                if (it != null) {
                    it.email = emailField.text.toString()
                }
                if (it != null) {
                    it.username = userNameField.text.toString()
                }
                if (it != null) {
                    it.phone_number = phoneNumberField.text.toString().toInt()
                }
            }
            editProfViewModel.updateProfileData()
        }

        editProfViewModel.successfullUpdate.observe(viewLifecycleOwner){
            val bundle = bundleOf("currUser" to App.thisUser.username)
            Navigation.findNavController(requireView()).navigate(R.id.navigateFromEditProfileFragmentToProfileFragment, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}