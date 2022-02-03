package com.example.bazar.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.widget.SwitchCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import com.example.bazar.App
import com.example.bazar.R
import com.example.bazar.databinding.FragmentAddProductBinding
import com.example.bazar.manager.SharedPreferencesManager
import com.example.bazar.repository.Repository
import com.example.bazar.viewmodels.AddProductViewModel
import com.example.bazar.viewmodels.AddProductViewModelFactory
import com.example.bazar.viewmodels.LoginViewModel
import com.example.bazar.viewmodels.LoginViewModelFactory
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.launch

class AddProductFragment : Fragment() {

    private var _binding : FragmentAddProductBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private lateinit var activeToggleButton : SwitchCompat
    private lateinit var prodTitle : EditText
    private lateinit var prodPrice : EditText
    private lateinit var prodUnit : EditText
    private lateinit var prodDescription : EditText
    private lateinit var phoneNumber : EditText
    private lateinit var launchMyFare : MaterialButton
    private lateinit var currencyType : Spinner
    private lateinit var amountType : Spinner

    private lateinit var addProdViewModel : AddProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = AddProductViewModelFactory(this.requireContext(), Repository())
        addProdViewModel = ViewModelProvider(this, factory).get(AddProductViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddProductBinding.inflate(inflater, container, false)
        val view = binding.root

        getViews()

        launchMyFare.setOnClickListener {
            onLaunchMyFareBtnClicked()
        }

        return view
    }

    fun getViews(){
        activeToggleButton = binding.fragAddProdActiveToggleButton
        prodTitle = binding.fragAddProdProdName
        prodPrice = binding.fragAddProdProdPricePerAmount
        prodUnit = binding.fragAddProdProdAvailableAmount
        prodDescription = binding.fragAddProdProdDescription
        phoneNumber = binding.fragAddProdProdPhoneNumber
        launchMyFare = binding.fragAddProdLaunchFareBtn

        currencyType = binding.fragAddProdCurrency
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.currency_arr,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            currencyType.adapter = adapter
        }

        amountType = binding.fragAddProdAmountType
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.amountType_arr,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            amountType.adapter = adapter
        }

    }

    fun onLaunchMyFareBtnClicked(){
        addProdViewModel.product.value.let {
            if (it != null) {
                it.title = prodTitle.text.toString()
            }
            if (it != null) {
                it.price_per_unit = prodPrice.text.toString()
            }
            if (it != null) {
                it.price_type = currencyType.selectedItem.toString()
            }
            if (it != null) {
                it.units = prodUnit.text.toString()
            }
            if (it != null) {
                it.amount_type = amountType.selectedItem.toString()
            }
            if (it != null) {
                it.description = prodDescription.text.toString()
            }
        }

        lifecycleScope.launch {
            addProdViewModel.uploadProductToDatabase()
        }

        addProdViewModel.successfullUpload.observe(viewLifecycleOwner){
            Navigation.findNavController(requireView()).navigate(R.id.navigateFromAddProductFragmentToMyMarketFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}