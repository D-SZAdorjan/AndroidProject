package com.example.bazar.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.example.bazar.App
import com.example.bazar.R
import com.example.bazar.databinding.FragmentLoginBinding
import com.example.bazar.databinding.FragmentRegisterBinding
import com.example.bazar.manager.SharedPreferencesManager
import com.example.bazar.repository.Repository
import com.example.bazar.viewmodels.LoginViewModel
import com.example.bazar.viewmodels.LoginViewModelFactory
import com.example.bazar.viewmodels.RegisterViewModel
import com.example.bazar.viewmodels.RegisterViewModelFactory
import kotlinx.coroutines.launch


class RegisterFragment : Fragment() {
    private var _binding : FragmentRegisterBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private lateinit var regViewModel: RegisterViewModel
    private lateinit var userNameET: EditText
    private lateinit var emailET: EditText
    private lateinit var passwordET: EditText
    private lateinit var registerBtn: Button
    private lateinit var backToLoginBtn: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = RegisterViewModelFactory(this.requireContext(), Repository())
        regViewModel = ViewModelProvider(this, factory).get(RegisterViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val view = binding.root

        userNameET = binding.regFragUsernameET
        emailET = binding.regFragEmailET
        passwordET = binding.regFragPasswET
        registerBtn = binding.regFragRegBtn
        backToLoginBtn = binding.regFragLogInBtn

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerBtn.setOnClickListener {
            onRegisterBtnClicked(view)
        }

        backToLoginBtn.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun onRegisterBtnClicked(view: View) {
        regViewModel.user.value.let {
            if (it != null) {
                it.username = userNameET.text.toString()
            }
            if (it != null) {
                it.password = passwordET.text.toString()
            }
            if (it != null) {
                Log.d("xxx", emailET.text.toString())
                it.email = emailET.text.toString()
            }
        }

        lifecycleScope.launch {
            regViewModel.register()
        }

        regViewModel.isSuccessfull.observe(this.viewLifecycleOwner) {
            Log.d("xxx", "Registered = " + it)
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}