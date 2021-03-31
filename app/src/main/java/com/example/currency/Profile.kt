package com.example.currency

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class Profile : AppCompatActivity() {


    lateinit var email: TextView
    lateinit var logout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)



        logout = findViewById(R.id.profbutton)

        loadProfile()



    }

    private fun loadProfile() {






        logout.setOnClickListener {
            //auth.signOut()
            FirebaseAuth.getInstance().signOut();
            getSharedPreferences("Loggedin", Context.MODE_PRIVATE).edit().putBoolean("isLoggedin", false).apply()
            startActivity(Intent(this, Login::class.java))
            Toast.makeText(this,"Logged Out Sucessfully",Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}