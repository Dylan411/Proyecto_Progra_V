package com.example.proyecto_progra5

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openFragment(HomeFragment())
        //btnAddSite.hide()
        btnNavbar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.homeFragment -> openFragment(HomeFragment())
                R.id.perfilFragment -> openFragment(PerilFragment())
                R.id.favoritesFragment -> openFragment(FavoritesFragment())
                R.id.searchFragment -> openFragment(SearchFragment())
                R.id.topFragment -> openFragment(TopFragment())

            }
            true
        }

        btnAddSite.setOnClickListener {
            startActivity(Intent(this, InsertActivity::class.java))
        }


    }


    // funcion para abrir fragments en el fragmentView
    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


}




