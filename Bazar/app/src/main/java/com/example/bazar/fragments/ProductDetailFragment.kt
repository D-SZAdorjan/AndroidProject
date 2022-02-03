package com.example.bazar.fragments

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.bazar.App
import com.example.bazar.R
import com.example.bazar.databinding.FragmentProductDetailBinding
import com.example.bazar.databinding.FragmentProfileBinding
import com.example.bazar.model.Product
import com.example.bazar.repository.Repository
import com.example.bazar.viewmodels.ProfileFragmentViewModel
import com.example.bazar.viewmodels.ProfileFragmentViewModelFactory
import java.security.acl.Group

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProductDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductDetailFragment : Fragment() {
    lateinit var currItem : Product
    lateinit var userImage : ImageView
    lateinit var userName : TextView
    lateinit var productName : TextView
    lateinit var date : TextView
    lateinit var productPrice : TextView
    lateinit var productCurency : TextView
    lateinit var productIsActive : TextView
    lateinit var productDescription : TextView
    lateinit var clickableUser : View
    lateinit var rating : TextView
    lateinit var pricePerUnit : TextView
    lateinit var unitNumber : TextView
    lateinit var availableItems : TextView
    lateinit var permission : androidx.constraintlayout.widget.Group

    private var _binding : FragmentProductDetailBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        val view = binding.root

        currItem = this.requireArguments().getParcelable<Product>("currItem")!!
        if( App.thisUser.username.compareTo( currItem.username ) == 0 ){
            setupViewForOwner(currItem)
        }
        else{
            setupViewForCustomer(currItem)
        }
        clickableUser.setOnClickListener{
            onUserBtnClick(view)
        }
        return view
    }

    fun setupViewForCustomer(currItem: Product){
        userName = binding.fragProdDetailOwnerName
        userImage = binding.fragProdDetailUserImage
        productName = binding.fragProdDetailProdName
        date = binding.fragProdDetailDate
        productPrice = binding.fragProdDetailPrice
        productCurency = binding.fragProdDetailCurrency
        productIsActive = binding.fragProdDetailIsActiveItem
        productDescription = binding.fragProdDetailDescription
        clickableUser = binding.fragProdDetailClickableOwner
        unitNumber = binding.fragProdDetailUnits
        pricePerUnit = binding.fragProdDetailPricePerItem
        rating = binding.fragProdDetailRating
        availableItems = binding.fragProdDetailAvailableAmount
        permission = binding.fragProdDetailCustomerView

        userName.text = currItem.username
        productName.text = currItem.title
        date.text = currItem.creation_time.toString()
        productPrice.text = currItem.price_per_unit
        productCurency.text = "${currItem.price_type}/${currItem.amount_type}"
        if(!currItem.is_active) productIsActive.text = "Inactive"
        productDescription.text = currItem.description
        unitNumber.text = "${currItem.units} ${currItem.amount_type}"
        pricePerUnit.text = currItem.price_per_unit
        rating.text = currItem.rating.toString()
        availableItems.text = "${availableItems.text}${currItem.units}"
        permission.visibility = View.VISIBLE
    }

    fun setupViewForOwner(currItem: Product){
        userName = binding.fragProdDetailOwnerName
        userImage = binding.fragProdDetailUserImage
        productName = binding.fragProdDetailProdName
        date = binding.fragProdDetailDate
        productPrice = binding.fragProdDetailPrice
        productCurency = binding.fragProdDetailCurrency
        productIsActive = binding.fragProdDetailIsActiveItem
        productDescription = binding.fragProdDetailDescription
        clickableUser = binding.fragProdDetailClickableOwner
        unitNumber = binding.fragProdDetailUnits
        pricePerUnit = binding.fragProdDetailPricePerItem
        rating = binding.fragProdDetailRating
        permission = binding.fragProdDetailOwnerView

        userName.text = currItem.username
        productName.text = currItem.title
        date.text = currItem.creation_time.toString()
        productPrice.text = currItem.price_per_unit
        productCurency.text = "${currItem.price_type}/${currItem.amount_type}"
        if(!currItem.is_active) productIsActive.text = "Inactive"
        productDescription.text = currItem.description
        unitNumber.text = "${currItem.units} ${currItem.amount_type}"
        pricePerUnit.text = currItem.price_per_unit
        rating.text = currItem.rating.toString()
        permission.visibility = View.VISIBLE
    }

    fun onUserBtnClick(view: View){
        val bundle = bundleOf("currUser" to userName.text)
        Navigation.findNavController(view).navigate(R.id.navigateFromProductDetailFragmentToProfileFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        permission.visibility = View.GONE
    }
}