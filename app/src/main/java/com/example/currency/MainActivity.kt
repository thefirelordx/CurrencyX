package com.example.currency

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.currency.databinding.ActivityMainBinding
import com.example.currency.fragments.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val convertFragment = ConvertFragment()
        val chartsFragment = ChartsFragment()
        val sendFragment = SendFragment()
        val favoritesFragment = FavoritesFragment()
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












