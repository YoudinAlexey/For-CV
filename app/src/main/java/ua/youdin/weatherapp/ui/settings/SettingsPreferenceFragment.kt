package ua.youdin.weatherapp.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.PreferenceFragmentCompat
import com.google.android.material.color.MaterialColors
import ua.youdin.weatherapp.R


class SettingsPreferenceFragment : PreferenceFragmentCompat() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        super.onCreateView(inflater, container, savedInstanceState)?.apply {
            setBackgroundColor(MaterialColors.getColor(this, R.attr.colorSurface))
        }


    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.main, rootKey)
    }

    companion object {
        val TEMPERATURE_UNITS = "temperature_units_preference"
        val LANGUE = "interface_language_preference"
    }
}
