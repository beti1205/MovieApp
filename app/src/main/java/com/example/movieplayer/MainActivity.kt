package com.example.movieplayer

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.movieplayer.databinding.ActivityMainBinding
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
            binding.bottomNavigation.isVisible =
                destination.id != R.id.movieDetailsFragment && destination.id != R.id.TVSeriesDetailsFragment
            binding.searchEditFrame.isVisible =
                destination.id == R.id.searchMoviesFragment || destination.id == R.id.searchTvSeriesFragment
            binding.searchEditFrame.setText("")
        }
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.movieFragment,
                R.id.TVSeriesFragment
            )
        )

        setSupportActionBar(binding.topAppBar)
        binding.topAppBar.setupWithNavController(navController, appBarConfiguration)
    }
}
