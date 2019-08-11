package com.wanderingthinkter.tummy

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                this.supportActionBar?.setTitle(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_recipes -> {
                this.supportActionBar?.setTitle(R.string.title_recipe)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                this.supportActionBar?.setTitle(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_friends -> {
                this.supportActionBar?.setTitle(R.string.title_friends_circle)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_account -> {
                this.supportActionBar?.setTitle(R.string.title_account)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.supportActionBar?.setTitle(R.string.title_home)

        setContentView(R.layout.activity_home)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }
}
