package com.example.currency.fragments


import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
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
                    it.hideKeyboard()


            )
        }


        lifecycleScope.launchWhenStarted {
            viewModel.conversion.collect { event ->
                when(event){
                   is MainViewModel.CurrencyEvent.Success -> {
                       binding.progressBar.isVisible = false
                       binding.tvResult.setTextColor(Color.BLACK)
                       binding.tvResult.text = event.resultsText
                       binding.tvsinglevalue.setTextColor(Color.BLUE)
                       binding.tvsinglevalue.text = event.rita
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

fun View.hideKeyboard(){
        val imm = requireContext().getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(this.windowToken,0)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

