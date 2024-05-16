package com.ren.zarinacapital.navigation

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.ren.menu.internal.presentation.ui.setting.preference.SharedPreferenceLanguages
import com.ren.presentation.utils.gone
import com.ren.presentation.utils.visible
import com.ren.zarinacapital.R
import com.ren.zarinacapital.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val sharedPreferenceLanguages: SharedPreferenceLanguages by lazy {
        SharedPreferenceLanguages(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currentLanguage = sharedPreferenceLanguages.getLanguage()

        setLocale(currentLanguage)
        setupNavController()

    }

    private fun setLocale(language: String) {
        val config = Configuration(resources.configuration)
        config.setLocale(Locale(language))
        resources.updateConfiguration(config, resources.displayMetrics)
        sharedPreferenceLanguages.saveLanguage(language)
    }
    

    private fun setupNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.courses,
                R.id.my_courses,
                R.id.menu,
                R.id.auth_flow
            )
        )

        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
        binding.bottomNavigation.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.auth_flow -> {
                    binding.toolbar.gone()
                    binding.bottomNavigation.gone()
                }

                else -> {
                    binding.toolbar.visible()
                    binding.bottomNavigation.visible()
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    companion object {
        private const val LANGUAGE_KEY = "language"
    }
}