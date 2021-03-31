package com.example.currency.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.currency.R
import com.example.currency.databinding.FragmentConvertBinding

class SendFragment:Fragment(R.layout.fragment_send){
    private  var _binding: FragmentConvertBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentConvertBinding.inflate(inflater,container,false)










        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

