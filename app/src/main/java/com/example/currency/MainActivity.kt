package com.example.currency

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.currency.databinding.ActivityMainBinding
import com.example.currency.fragments.*
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


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
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.myToolbar)

        val isLoggedin = getSharedPreferences("Loggedin", Context.MODE_PRIVATE).getBoolean("isLoggedin", false)



        supportActionBar?.setIcon(R.drawable.ic_user)
        supportActionBar?.title = ""

        binding.myToolbar.setOnClickListener {
            if(isLoggedin){
                startActivity(Intent(this,Profile::class.java))
            }
            else{
                startActivity(Intent(this, Login::class.java))
            }
        }




        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val convertFragment = ConvertFragment()
        val chartsFragment = ChartsFragment()
        val sendFragment = SendFragment()
        val favoritesFragment = NewsFragment()
        val moreFragment = MoreFragment()

        makeCurrentFragment(convertFragment)
        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.ic_rupee -> makeCurrentFragment(convertFragment)
                R.id.ic_charts -> makeCurrentFragment(chartsFragment)
                R.id.ic_send_money -> makeCurrentFragment(sendFragment)
                R.id.ic_favorites -> makeCurrentFragment(favoritesFragment)
                R.id.ic_more -> makeCurrentFragment(moreFragment)

            }
            true


        }

    }



    private fun makeCurrentFragment(fragment: Fragment) =
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fl_wrapper, fragment)
                commit()
            }


    }














