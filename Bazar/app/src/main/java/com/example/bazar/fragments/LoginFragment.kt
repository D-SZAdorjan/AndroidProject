package com.example.bazar.fragments

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
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
import androidx.navigation.Navigation
import com.example.bazar.App
import com.example.bazar.MainScreen
import com.example.bazar.R
import com.example.bazar.databinding.FragmentLoginBinding
import com.example.bazar.repository.Repository
import com.example.bazar.viewmodels.LoginViewModel
import com.example.bazar.viewmodels.LoginViewModelFactory
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private var _binding : FragmentLoginBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private lateinit var loginViewModel: LoginViewModel
    lateinit var signInBtn : Button
    lateinit var loginUserName : EditText
    lateinit var loginPassword : EditText
    lateinit var registerBtn : Button
    lateinit var forgotPassBtn : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = LoginViewModelFactory(this.requireContext(), Repository())
        loginViewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root

        loginUserName = binding.userName
        loginPassword = binding.password
        signInBtn = binding.signInBtn
        registerBtn = binding.loginFragRegisterBtn
        forgotPassBtn = binding.loginFragFPBtn

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signInBtn.setOnClickListener {
            onSignInBtnClicked(view)
        }

        forgotPassBtn.setOnClickListener{
            onForgotPasswordTextClicked(view)
        }

        registerBtn.setOnClickListener {
            onRegisterButtonClicked(view)
        }
    }

    private fun onForgotPasswordTextClicked(view: View){
        Navigation.findNavController(view).navigate(R.id.navigateFromLoginFragmentToForgotPasswordFragment)
    }

    private fun onRegisterButtonClicked(view: View){
        Navigation.findNavController(view).navigate(R.id.navigateFromLoginFragmentToRegisterFragment)
    }

    private fun onSignInBtnClicked(view: View) {
        loginViewModel.user.value.let {
            if (it != null) {
                it.username = loginUserName.text.toString()
            }
            if (it != null) {
                it.password = loginPassword.text.toString()
            }
        }

        lifecycleScope.launch {
            loginViewModel.login()
        }

        loginViewModel.isSuccessfull.observe(this.viewLifecycleOwner) {
            Log.d("xxx", "Logged in successfully = " + it)
            openMainScreen(App.token)
        }
    }

    fun openMainScreen(token : String){
        val intent = Intent(activity, MainScreen::class.java).apply{
            Log.d("xxx", "Token in openMainScreen function: " + token)
            putExtra(EXTRA_MESSAGE, token)
        }
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}