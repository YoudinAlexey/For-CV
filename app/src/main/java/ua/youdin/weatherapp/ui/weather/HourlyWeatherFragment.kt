package ua.youdin.weatherapp.ui.weather


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import ua.youdin.weatherapp.R
import ua.youdin.weatherapp.databinding.HourlyWeatherFragmentBinding
import ua.youdin.weatherapp.servise.setupRefreshLayout
import ua.youdin.weatherapp.ui.mainactivity.MainActivity.Companion.STATE_CURRENT_CITY
import ua.youdin.weatherapp.ui.mainactivity.MainActivityViewModel


@KoinApiExtension
class HourlyWeatherFragment : Fragment(R.layout.hourly_weather_fragment), KoinComponent {
    private var _binding: HourlyWeatherFragmentBinding? = null
    private val binding get() = _binding!!
    private val mainActivityViewModel by sharedViewModel<MainActivityViewModel>()
    private val hourlyWeatherViewModel: HourlyWeatherViewModel by viewModel()
    private lateinit var hourlyWeatherAdapter: HourlyWeatherAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HourlyWeatherFragmentBinding.inflate(inflater, container, false)
        binding.mainactivityviewmodel = mainActivityViewModel
        binding.viewmodel = hourlyWeatherViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        arguments?.let {
            it.getInt(STATE_CURRENT_CITY)
                .let { hourlyWeatherViewModel.getListHourlyWeatherDBByCityID(it) }
        }
        setupRefreshLayout(binding.hourlyRefreshLayout, binding.hourlyWeatherList)
        hourlyWeatherAdapter = HourlyWeatherAdapter(
            viewModel = hourlyWeatherViewModel,
            temperature_units = mainActivityViewModel.temperature_units_preference,
            interface_language = mainActivityViewModel.interface_language_preference
        )
        binding.hourlyWeatherList.adapter = hourlyWeatherAdapter
        hourlyWeatherViewModel.listHourlyWeatherRecycler.observe(viewLifecycleOwner, {
            it?.let {
                hourlyWeatherAdapter.submitList(it)
            }
        })
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(currentOpeningCity: Int) =
            HourlyWeatherFragment().apply {
                arguments = Bundle().apply {
                    putInt(STATE_CURRENT_CITY, currentOpeningCity)
                }
            }
    }
}

