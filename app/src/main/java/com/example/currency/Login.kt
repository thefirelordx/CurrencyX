package com.example.currency

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.currency.databinding.LoginBinding
import com.google.firebase.auth.FirebaseAuth


class Login : AppCompatActivity() {

    private lateinit var binding: LoginBinding
    private lateinit var auth: FirebaseAuth

    lateinit var passwordtext: EditText
    lateinit var emailtext: EditText

    lateinit var email: String
    lateinit var password: String


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm: InputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        passwordtext = findViewById(R.id.passtext)
        emailtext = findViewById(R.id.emailtext)

        val forgroundSpan = "Don’t have an account? Register"
        val forgroundSpannableString = SpannableString(forgroundSpan)
        forgroundSpannableString.setSpan(
            ForegroundColorSpan(Color.BLUE),
            23,
            31,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
        binding.textView3.text = forgroundSpannableString


        binding.logToolbar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.textView3.setOnClickListener {
            startActivity(Intent(this,Signup::class.java))
        }

        emailtext.text

        auth = FirebaseAuth.getInstance()

        fun loginAccount (email:String,password:String) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.i("Login Email", "signInWithEmail:success")
                        val user = auth.currentUser

                        if (user!!.isEmailVerified())
                        {

                            // user is verified, so you can finish this activity or send user to activity which you want.
                            startActivity(Intent(this,MainActivity::class.java))
                            Toast.makeText(this, "Login Succcessful", Toast.LENGTH_SHORT).show()
                            getSharedPreferences("Loggedin", Context.MODE_PRIVATE).edit().putBoolean("isLoggedin", true).apply()

                        }
                        else
                        {
                            // email is not verified, so just prompt the message to the user and restart this activity.
                            Toast.makeText(this, "Email is not verified", Toast.LENGTH_SHORT).show()
                        }
                    } else if(task.exception.toString() == "com.google.firebase.auth.FirebaseAuthInvalidUserException: There is no user record corresponding to this identifier. The user may have been deleted."){
                        Toast.makeText(this, "User doesn't exit", Toast.LENGTH_SHORT).show()
                    }
                    else if (task.exception.toString() == "com.google.firebase.auth.FirebaseAuthInvalidCredentialsException: The email address is badly formatted.") {
                        // If sign in fails, display a message to the user.
                        Log.i("Error of",task.exception.toString())
                        Toast.makeText(this, "Email is invalid", Toast.LENGTH_SHORT).show()
                    }
                    else if (task.exception.toString() == "com.google.firebase.auth.FirebaseAuthInvalidCredentialsException: The password is invalid or the user does not have a password."){
                        Toast.makeText(this, "Password is incorrect", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        // If sign in fails, display a message to the user.
                        Log.i("Error of", task.exception.toString())
                        Toast.makeText(this,"Unknown error occured",Toast.LENGTH_SHORT).show()
                        // ...
                    }

                    // ...
                }
        }

        fun Loginfun (v: View){
            if (emailtext.text.isNullOrEmpty() || passwordtext.text.isNullOrEmpty())
            {
                Toast.makeText(this, "You can't leave a field empty!", Toast.LENGTH_SHORT).show()

            }

            else {
                email = emailtext.text.toString()
                password = passwordtext.text.toString()
                loginAccount(email,password)
            }

        }
        binding.button.setOnClickListener {
            Loginfun(View(this))
        }

    }





}

