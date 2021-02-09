package ua.youdin.weatherapp.ui.findCity

import android.Manifest
import android.content.pm.PackageManager
import android.content.res.Resources
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.EditorInfo
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.preference.PreferenceManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.material.color.MaterialColors
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension
import ua.youdin.weatherapp.R
import ua.youdin.weatherapp.databinding.FindCityFragmentBinding
import ua.youdin.weatherapp.servise.EventObserver
import ua.youdin.weatherapp.servise.SharedPreferencesDelegated
import ua.youdin.weatherapp.servise.hideKeyboard
import ua.youdin.weatherapp.ui.mainactivity.MainActivity
import ua.youdin.weatherapp.ui.settings.SettingsPreferenceFragment.Companion.LANGUE
import ua.youdin.weatherapp.ui.settings.SettingsPreferenceFragment.Companion.TEMPERATURE_UNITS
import kotlin.properties.Delegates


class FindCityFragment : Fragment(R.layout.find_city_fragment), OnMapReadyCallback,
    GoogleMap.OnMyLocationClickListener, GoogleMap.OnMyLocationButtonClickListener {
    private var _binding: FindCityFragmentBinding? = null
    private val binding get() = _binding!!
    private val findCityViewModel: FindCityViewModel by viewModel()
    private var mapFragment: SupportMapFragment? = null
    private lateinit var map: GoogleMap
    private var isNightTheme by Delegates.notNull<Boolean>()
    private var temperature_units_preference by Delegates.notNull<Boolean>()
    private var interface_language_preference by Delegates.notNull<String>()
    private var animAlpha: Animation? = null
    private var _selectedCitylotlang = MutableLiveData<Pair<String, LatLng>>()
    private var selectedCitylotlang: LiveData<Pair<String, LatLng>> = _selectedCitylotlang

    @KoinApiExtension
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FindCityFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = findCityViewModel
        binding.lifecycleOwner = this.viewLifecycleOwner
        val view = binding.root
        var currentOpeningCity by SharedPreferencesDelegated(
            context = requireContext(),
            key = MainActivity.STATE_CURRENT_CITY,
            defaultValue = MainActivity.DEFAULT_CITY
        )
        PreferenceManager.getDefaultSharedPreferences(context).also { sharedPreferences ->
            temperature_units_preference = sharedPreferences.getBoolean(TEMPERATURE_UNITS, false)
            interface_language_preference = sharedPreferences.getString(LANGUE, "en") ?: "en"
        }
        isNightTheme = resources.getBoolean(R.bool.isNightTheme)
        setConfirmButtonVisibility(isenabled = false)
        animAlpha = AnimationUtils.loadAnimation(requireContext(), R.anim.alpha)
        observeLoadCoordinatesAfterLoad()
        observeLoadCitiesAfterLoad()
        observeLoadCoordinatesAfterFind()
        mapFragment = childFragmentManager.findFragmentById(R.id.map_for_find) as SupportMapFragment
        mapFragment?.getMapAsync(this)
        val textFieldFindCity = binding.textFieldFindCity

        textFieldFindCity.setEndIconOnClickListener {
            loadCoordinates(textFieldFindCity, view)
        }

        binding.textFieldFindCityInputEditText.setOnEditorActionListener { _, actionId, _ ->//v, actionId, event
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                loadCoordinates(textFieldFindCity, view)
                true
            } else {
                false
            }
        }

        binding.buttonYes.setOnClickListener {
            //записать в бд горолд
            selectedCitylotlang.value?.let {
                findCityViewModel.recordSelectedCity(
                    it,
                    temperature_units_preference,
                    interface_language_preference
                )
                currentOpeningCity = it.first
                setConfirmButtonVisibility(isenabled = false)
            }
        }

        return view
    }

    private fun loadCoordinates(
        textFieldFindCity: TextInputLayout,
        view: View
    ) {
        findCityViewModel.loadCoordinates(
            textFieldFindCity.editText?.text.toString(),
            interface_language_preference
        )
        this.hideKeyboard(view)
    }

    private fun observeLoadCoordinatesAfterLoad() {
        findCityViewModel.loadedCoordinates.observe(viewLifecycleOwner, EventObserver { listGeo ->
            if (listGeo.isEmpty()) binding.textFieldFindCity.error =
                resources.getString(R.string.errorFindCity)
            else {
                confirmCityDialog(listGeo)
            }
        })
    }

    private fun observeLoadCitiesAfterLoad() {
        findCityViewModel.loadedSities.observe(viewLifecycleOwner, EventObserver { listCities ->
            if (listCities.isEmpty()) binding.textFieldFindCity.error =
                resources.getString(R.string.errorFindCity)
            else {
                confirmCityDialog(listCities)
            }
        })
    }

    private fun observeLoadCoordinatesAfterFind() {
        selectedCitylotlang.observe(viewLifecycleOwner, { latlng ->
            map.moveCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(
                        latlng.second.latitude,
                        latlng.second.longitude
                    ), DEFAULT_ZOOM
                )
            )
        })
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        if (googleMap != null) {
            map = googleMap
            if (isNightTheme) try {
                map.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                        requireContext(), R.raw.google_dark_theme
                    )
                )
            } catch (e: Resources.NotFoundException) {
                Log.e(TAG, "Can't find style. Error: ", e)
            }
        }
        map.let { map ->
            map.setOnMyLocationClickListener(this)
            map.setOnMyLocationButtonClickListener(this)
            map.setOnMapLongClickListener { location ->
                findCityViewModel.loadCities(
                    location.latitude,
                    location.longitude,
                    interface_language_preference
                )
            }
        }
        enableMyLocation()
    }

    private fun enableMyLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                REQUEST_LOCATION_PERMISSION
            )
            return
        }
        map.isMyLocationEnabled = true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray,
    ) {
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.contains(PackageManager.PERMISSION_GRANTED)) {
                enableMyLocation()
            }
        }
    }

    override fun onMyLocationClick(location: Location) {
        findCityViewModel.loadCities(
            location.latitude,
            location.longitude,
            interface_language_preference
        )
    }

    override fun onMyLocationButtonClick(): Boolean {
        Snackbar.make(
            binding.root,
            resources.getString(R.string.on_my_location_button_click),
            Snackbar.LENGTH_LONG
        ).show()
        this.hideKeyboard(binding.root)
        return false
    }

    fun confirmCityDialog(listCities: Map<String, LatLng>) {
        val items = arrayListOf<String>()
        for ((key, _) in listCities) {
            items.add(key)
        }
        if (listCities.size == 1) {
            _selectedCitylotlang.value = items[0] to (listCities.getValue(items[0]))
            setConfirmButtonVisibility(isenabled = true)
        } else {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(resources.getString(R.string.confirmCityDialogTitle))
                .setNeutralButton(resources.getString(R.string.cancel)) { dialog, _ ->
                    _selectedCitylotlang = MutableLiveData<Pair<String, LatLng>>()
                    setConfirmButtonVisibility(isenabled = false)
                    dialog.cancel()
                }
                .setPositiveButton(resources.getString(R.string.ok)) { dialog, _ ->
                    _selectedCitylotlang.value = items[0] to (listCities.getValue(items[0]))
                    setConfirmButtonVisibility(isenabled = true)
                    dialog.dismiss()
                }
                .setSingleChoiceItems(items.toTypedArray(), 0) { dialog, which ->
                    _selectedCitylotlang.value = items[which] to (listCities.getValue(items[which]))
                    setConfirmButtonVisibility(isenabled = true)
                    dialog.dismiss()
                }
                .show()
        }
    }

    private fun setConfirmButtonVisibility(isenabled: Boolean) {
        animAlpha?.let { binding.buttonYes.startAnimation(it) }
        binding.buttonYes.isEnabled = isenabled
        if (isenabled) {
            binding.buttonYes.setBackgroundColor(
                MaterialColors.getColor(
                    binding.root,
                    R.attr.colorSecondaryVariant
                )
            )
            binding.buttonYes.setTextColor(R.attr.colorOnSurface)
        } else
            MaterialColors.getColor(
                binding.root,
                R.attr.colorOnSurface
            )
    }

    companion object {
        const val TAG = "FindCityDialog"
        const val KEY_CITY = "KEY_CITY"
        const val KEY_LATITUDE = "KEY_LATITUDE"//latitude
        const val KEY_LONGTIDUDE = "KEY_LONGTIDUDE" //longitude
        const val KEY_TEMPERATURE_UNITS = "KEY_TEMPERATURE_UNITS" //temperature_units
        const val KEY_LANGUAGE = "KEY_LANGUAGE" //interface_language
        private const val REQUEST_LOCATION_PERMISSION = 1
        private const val DEFAULT_ZOOM = 9.5f
    }
}
