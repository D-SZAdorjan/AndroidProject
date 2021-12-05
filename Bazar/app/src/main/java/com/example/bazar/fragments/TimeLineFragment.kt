package com.example.bazar.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.bazar.R
import com.example.bazar.databinding.FragmentLoginBinding
import com.example.bazar.databinding.FragmentTimeLineBinding
import com.example.bazar.viewmodels.MainScreenViewModel

class TimeLineFragment : Fragment() {

    private var _binding : FragmentTimeLineBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private val mainscreenviewmodel : MainScreenViewModel by activityViewModels()
    lateinit var tokenText : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTimeLineBinding.inflate(inflater, container, false)
        val view = binding.root

        tokenText = binding.fragTLTOKEN
        tokenText.text = mainscreenviewmodel.token.value
        Log.d("xxx","token in TimeLineFragment: " + mainscreenviewmodel.token.value)

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}