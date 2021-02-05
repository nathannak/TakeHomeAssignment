package com.demo.demoapplication.view

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.demo.demoapplication.R
import com.demo.demoapplication.databinding.FragmentStoreDetailsBinding
import kotlinx.android.synthetic.main.fragment_store_details.view.*

class StoreDetailsFragment : Fragment() {

    private val args: StoreDetailsFragmentArgs by navArgs()
    private lateinit var fragmentStoreDetailsBinding: FragmentStoreDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        fragmentStoreDetailsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_store_details, container, false
        )


        return fragmentStoreDetailsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val store = args.store
        fragmentStoreDetailsBinding.store = store
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()

    }

}