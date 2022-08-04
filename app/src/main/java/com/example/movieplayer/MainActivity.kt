package com.example.movieplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.EditText
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.movieplayer.databinding.ActivityMainBinding
import com.example.movieplayer.databinding.MovieDetailBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val searchEditText: EditText
        get() = binding.searchEditFrame

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bottomNavigation.isVisible = destination.id != R.id.movieDetailsFragment
            binding.searchEditFrame.isVisible = destination.id == R.id.searchMoviesFragment
        }

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.movieFragment,
                R.id.TVSeriesFragment,
                R.id.peopleFragment
            )
        )

        setSupportActionBar(binding.topAppBar)
        binding.topAppBar.setupWithNavController(navController, appBarConfiguration)
    }

}
