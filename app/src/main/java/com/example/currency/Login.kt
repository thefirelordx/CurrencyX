package com.example.currency

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.appcompat.app.AppCompatActivity
import com.example.currency.databinding.LoginBinding

class Login : AppCompatActivity() {

    private lateinit var binding: LoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val forgroundSpan = "Don’t have an account? Register"
        val forgroundSpannableString = SpannableString(forgroundSpan)
        forgroundSpannableString.setSpan(ForegroundColorSpan(Color.BLUE),23,31,Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        binding.textView3.text = forgroundSpannableString


        binding.logToolbar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }


    }


}

