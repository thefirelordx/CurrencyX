package com.example.currency

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.currency.databinding.SignupBinding
import com.google.firebase.auth.FirebaseAuth


class Signup : AppCompatActivity() {
    private lateinit var binding: SignupBinding
    private lateinit var auth: FirebaseAuth

    lateinit var passwordtext: EditText
    lateinit var emailtext: EditText
    lateinit var usernametext: EditText
    lateinit var signup: Button

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
        binding = SignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logToolbar.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        auth = FirebaseAuth.getInstance()

        passwordtext = findViewById(R.id.suppasstext)
        emailtext = findViewById(R.id.supemailtext)


        fun createAccount (email:String ,password:String){
            Log.d("Create","Account")
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("Successful", "createUserWithEmail:success")
                        val user = auth.currentUser
                        user!!.sendEmailVerification()
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Toast.makeText(this, "You can't leave this field empty!", Toast.LENGTH_SHORT).show()
                                    Log.d("Email", "Email sent.")
                                    //Opens email app
                                    Handler().postDelayed(Runnable {
                                        val intent =
                                            packageManager.getLaunchIntentForPackage("com.google.android.gm")
                                        startActivity(intent)
                                        finish()
                                    }, 2000)
                                }
                            }


                    } else if (task.exception.toString() == "com.google.firebase.auth.FirebaseAuthInvalidCredentialsException: The email address is badly formatted.") {
                        // If sign in fails, display a message to the user.
                        Log.i("Error of",task.exception.toString())
                        Toast.makeText(this, "Email Address is Invalid!", Toast.LENGTH_SHORT).show()
                    }
                    else if(task.exception.toString() == "com.google.firebase.auth.FirebaseAuthWeakPasswordException: The given password is invalid. [ Password should be at least 6 characters ]"){
                        Log.i("Error of",task.exception.toString())
                        Toast.makeText(this, "Password is too Small! (At least 6 characters)", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        Toast.makeText(this, "Unknown Error Occurred!", Toast.LENGTH_SHORT).show()
                    }

                    // ...
                }

        }

        fun signupfun (v: View){
            if (emailtext.text.isNullOrEmpty() || passwordtext.text.isNullOrEmpty())
            {
                Log.d("Empty","fields")
                Toast.makeText(this, "You can't leave this field empty!", Toast.LENGTH_SHORT).show()
            }
            else {
                email = emailtext.text.toString()
                password = passwordtext.text.toString()
                createAccount(email,password)
            }

        }

        binding.button.setOnClickListener {
            signupfun(View(this))

        }



    }








}

