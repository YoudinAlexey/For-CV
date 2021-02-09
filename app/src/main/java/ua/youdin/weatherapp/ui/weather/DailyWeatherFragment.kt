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
import ua.youdin.weatherapp.databinding.DailyWeatherFragmentBinding
import ua.youdin.weatherapp.servise.setupRefreshLayout
import ua.youdin.weatherapp.ui.mainactivity.MainActivity.Companion.STATE_CURRENT_CITY
import ua.youdin.weatherapp.ui.mainactivity.MainActivityViewModel


@KoinApiExtension
class DailyWeatherFragment : Fragment(R.layout.daily_weather_fragment), KoinComponent {
    private var _binding: DailyWeatherFragmentBinding? = null
    private val binding get() = _binding!!
    private val mainActivityViewModel by sharedViewModel<MainActivityViewModel>()
    private val dailyWeatherViewModel: DailyWeatherViewModel by viewModel()
    private lateinit var dailyWeatherAdapter: DailyWeatherAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DailyWeatherFragmentBinding.inflate(inflater, container, false)
        binding.mainactivityviewmodel = mainActivityViewModel
        binding.viewmodel = dailyWeatherViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        arguments?.let {
            it.getInt(STATE_CURRENT_CITY)
                .let { dailyWeatherViewModel.getListDailyWeatherDBByCityID(it) }
        }
        setupRefreshLayout(binding.dealyRefreshLayout, binding.dailyWeatherList)
        dailyWeatherAdapter = DailyWeatherAdapter(
            viewModel = dailyWeatherViewModel,
            temperature_units = mainActivityViewModel.temperature_units_preference,
            interface_language = mainActivityViewModel.interface_language_preference
        )
        binding.dailyWeatherList.adapter = dailyWeatherAdapter
        dailyWeatherViewModel.listDailyWeatherRecycler.observe(viewLifecycleOwner, {
            it?.let {
                dailyWeatherAdapter.submitList(it)
//                dailyWeatherAdapter.notifyDataSetChanged()
            }
        })

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(currentOpeningCity: Int) =
            DailyWeatherFragment().apply {
                arguments = Bundle().apply {
                    putInt(STATE_CURRENT_CITY, currentOpeningCity)
                }
            }
    }
}

