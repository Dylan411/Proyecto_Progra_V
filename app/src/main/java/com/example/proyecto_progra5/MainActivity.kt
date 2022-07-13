package com.example.proyecto_progra5

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        //nav
//        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
//        val navController = findNavController(R.id.fragmentContainerView)
//
//        bottomNavigationView.setupWithNavController(navController)
//
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.homeFragment, R.id.searchFragment, R.id.topFragment, R.id.perfilFragment
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//
//        val intent = Intent(this, LoginActivity::class.java)
//
//        startActivity(intent)
//
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.homeFragment -> {
                    val fragment = HomeFragment.newInstance()
                    openFragment(fragment)
                    true
                }

                R.id.perfilFragment -> {
//                    val fragment = PerilFragment.newInstance()
//                    openFragment(fragment)
                    val intent = Intent(this,LoginActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.favoritesFragment -> {
                    val fragment = FavoritesFragment.newInstance()
                    openFragment(fragment)
                    true
                }

                R.id.searchFragment -> {
                    val fragment = SearchFragment.newInstance()
                    openFragment(fragment)
                    true
                }

                R.id.topFragment -> {
                    val fragment = TopFragment.newInstance()
                    openFragment(fragment)
                    true
                }
                else -> false
            }
        }
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}