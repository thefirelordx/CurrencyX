package com.example.currency.fragments


import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.currency.R
import com.example.currency.databinding.FragmentConvertBinding
import com.example.currency.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
@AndroidEntryPoint

class ConvertFragment : Fragment(R.layout.fragment_convert) {
    private val  viewModel: MainViewModel by viewModels()
    private  var _binding: FragmentConvertBinding? = null
   private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentConvertBinding.inflate(inflater,container,false)

        binding.btnConvert.setOnClickListener {
            viewModel.convert(
                    binding.etFrom.text.toString(),
                    binding.spFromCurrency.selectedItem.toString(),
                    binding.spToCurrency.selectedItem.toString(),
            )
        }

        lifecycleScope.launchWhenStarted {
            viewModel.conversion.collect { event ->
                when(event){
                   is MainViewModel.CurrencyEvent.Success -> {
                       binding.progressBar.isVisible = false
                       binding.tvResult.setTextColor(Color.BLACK)
                       binding.tvResult.text = event.resultsText
                    }
                   is MainViewModel.CurrencyEvent.Failure -> {
                       binding.progressBar.isVisible = false
                       binding.tvResult.setTextColor(Color.RED)
                       binding.tvResult.text = event.errorText
                   }
                   is MainViewModel.CurrencyEvent.Loading ->{
                       binding.progressBar.isVisible = true
                   }
                   else -> Unit
                }

            }
        }


        return binding.root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

