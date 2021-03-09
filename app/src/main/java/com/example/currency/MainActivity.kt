package com.example.currency

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toolbar
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.currency.databinding.ActivityMainBinding
import com.example.currency.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var toolbar: ActionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        toolbar = supportActionBar!!
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.ic_rupee -> {
                    toolbar.title = "Convert"
                    val convertFragment = ConvertFragment.newInstance()
                    openFragment(convertFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.ic_charts -> {
                    toolbar.title = "Charts"
                    val chartsFragment = ChartsFragment.newInstance()
                    openFragment(chartsFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.ic_send_money -> {
                    toolbar.title = "Send"
                    val sendFragment = SendFragment.newInstance()
                    openFragment(sendFragment)
                    return@OnNavigationItemSelectedListener true
                }

                R.id.ic_favorites -> {
                    toolbar.title = "Favorites"
                    val favoritesFragment = FavoritesFragment.newInstance()
                    openFragment(favoritesFragment)
                    return@OnNavigationItemSelectedListener true
                }

                R.id.ic_more -> {
                    toolbar.title = "More"
                    val moreFragment = MoreFragment.newInstance()
                    openFragemnt(moreFragment)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }
        fun openFragemnt(fragment: Fragment) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fl_wrapper, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

    }
}











