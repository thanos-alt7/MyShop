package com.example.myshop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.myshop.databinding.FragmentCheckoutBinding
import androidx.navigation.fragment.findNavController

class CheckoutFragment : Fragment() {

    private var _binding: FragmentCheckoutBinding? = null
    private val binding get() = _binding!!

    private val args: CheckoutFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCheckoutBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        binding.txtProductName.text = args.productName

        binding.edtAddress.setOnClickListener {
            findNavController().navigate(
                R.id.action_checkoutFragment_to_addressFragment
            )
        }

        findNavController().currentBackStackEntry
            ?.savedStateHandle
            ?.getLiveData<String>("selected_province")
            ?.observe(viewLifecycleOwner) { province ->
                binding.edtAddress.setText(province)
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
