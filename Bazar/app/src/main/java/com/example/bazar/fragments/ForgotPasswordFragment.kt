package com.example.bazar.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.bazar.R
import com.example.bazar.databinding.FragmentForgotPasswordBinding
import com.example.bazar.databinding.FragmentLoginBinding
import com.example.bazar.repository.Repository
import com.example.bazar.viewmodels.ForgotPasswordViewModel
import com.example.bazar.viewmodels.ForgotPasswordViewModelFactory
import com.example.bazar.viewmodels.RegisterViewModel
import com.example.bazar.viewmodels.RegisterViewModelFactory
import kotlinx.coroutines.launch

class ForgotPasswordFragment : Fragment() {
    private var _binding : FragmentForgotPasswordBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private lateinit var fpViewModel: ForgotPasswordViewModel
    private lateinit var emailET : EditText
    private lateinit var usernameET: EditText
    private lateinit var emailMeBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ForgotPasswordViewModelFactory(this.requireContext(), Repository())
        fpViewModel = ViewModelProvider(this, factory).get(ForgotPasswordViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        val view = binding.root

        emailET = binding.forpassFragEmailET
        usernameET = binding.forpassFragUsername
        emailMeBtn = binding.forpassFragEmailMeBtn

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        emailMeBtn.setOnClickListener {
            onSendMeEmailBtnClicked(view)
        }
    }

    private fun onSendMeEmailBtnClicked(view: View) {
        fpViewModel.user.value.let {
            if (it != null) {
                it.username = usernameET.text.toString()
            }
            if (it != null) {
                it.email = emailET.text.toString()
            }
        }

        lifecycleScope.launch {
            fpViewModel.sendPasswordResetEmail()
        }

        fpViewModel.isSuccessfull.observe(this.viewLifecycleOwner) {
            Log.d("xxx", "Registered = " + it)
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}