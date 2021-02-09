package ua.youdin.weatherapp.ui.mainactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.preference.PreferenceManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import ua.youdin.weatherapp.R
import ua.youdin.weatherapp.databinding.ActivityMainBinding
import ua.youdin.weatherapp.ui.settings.SettingsPreferenceFragment.Companion.LANGUE
import ua.youdin.weatherapp.ui.settings.SettingsPreferenceFragment.Companion.TEMPERATURE_UNITS


@KoinApiExtension
class MainActivity : AppCompatActivity(), KoinComponent {

    private lateinit var binding: ActivityMainBinding
    private val mainActivityViewModel by viewModel<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PreferenceManager.setDefaultValues(this, R.xml.main, false)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MobileAds.initialize(this) {}
        val adRequest = AdRequest.Builder().build()
        binding.adView.adView.loadAd(adRequest)


        val host: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment?
                ?: return
        val navController = host.navController
        binding.bottomNavigationView.setupWithNavController(navController = navController)

        PreferenceManager.getDefaultSharedPreferences(this).also { sharedPreferences ->
            mainActivityViewModel.refreshPreferences(
                sharedPreferences.getBoolean(TEMPERATURE_UNITS, false),
                sharedPreferences.getString(LANGUE, "en") ?: "en"
            )
            sharedPreferences.registerOnSharedPreferenceChangeListener { sPreferences, _ ->
                mainActivityViewModel.refreshPreferences(
                    sPreferences.getBoolean(TEMPERATURE_UNITS, false),
                    sPreferences.getString(LANGUE, "en") ?: "en"
                )
                mainActivityViewModel.refreshAll()
            }
        }
        mainActivityViewModel.selectionCities()
        binding.bottomNavigationView.elevation = 15f
    }

    companion object {
        val STATE_CURRENT_CITY = "CurrentOpeningCity"
        val DEFAULT_CITY = "Select the desired city"
    }
}




