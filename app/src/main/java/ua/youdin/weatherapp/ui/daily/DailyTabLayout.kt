package ua.youdin.weatherapp.ui.daily

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import ua.youdin.weatherapp.R
import ua.youdin.weatherapp.WeatherApplication
import ua.youdin.weatherapp.data.sourse.localSqlApi.tables.CitiesUserSelectedDB
import ua.youdin.weatherapp.databinding.DailyTabLayoutFragmentBinding
import ua.youdin.weatherapp.servise.SharedPreferencesDelegated
import ua.youdin.weatherapp.ui.mainactivity.MainActivity
import ua.youdin.weatherapp.ui.mainactivity.MainActivityViewModel
import ua.youdin.weatherapp.ui.weather.DailyWeatherFragment
import ua.youdin.weatherapp.work.LoadDataWorker.Companion.LOADDATAWORKER_NAME

@KoinApiExtension
class DailyTabLayout : Fragment(R.layout.daily_tab_layout_fragment), KoinComponent {
    private var _binding: DailyTabLayoutFragmentBinding? = null
    private val binding get() = _binding!!
    private val mainActivityViewModel by sharedViewModel<MainActivityViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DailyTabLayoutFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        val view = binding.root
        var currentOpeningCity by SharedPreferencesDelegated(
            context = requireContext(),
            key = MainActivity.STATE_CURRENT_CITY,
            defaultValue = MainActivity.DEFAULT_CITY
        )
        val workManager =
            WorkManager.getInstance(requireContext().applicationContext as WeatherApplication)
        val outputWorkInfos: LiveData<List<WorkInfo>> = workManager.getWorkInfosByTagLiveData(
            LOADDATAWORKER_NAME
        )

        outputWorkInfos.observe(viewLifecycleOwner, workInfosObserver())
        observeSelectedCitiesAndInitializeTabsView(currentOpeningCity)

        binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                currentOpeningCity = tab.text as String
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            binding.tabLayout.elevation = 5f
        }
        return view
    }

    private fun workInfosObserver(): Observer<List<WorkInfo>> {
        return Observer { listOfWorkInfo ->
            if (listOfWorkInfo.isNullOrEmpty()) {
                return@Observer
            }
            val workInfo = listOfWorkInfo[0]
            if (workInfo.state.isFinished) {
                mainActivityViewModel.selectionCities()
            }
        }
    }

    private fun observeSelectedCitiesAndInitializeTabsView(currentOpeningCity: String) {
        mainActivityViewModel.userCities.observe(viewLifecycleOwner, { userCities ->
            if (currentOpeningCity == MainActivity.DEFAULT_CITY) {
                val navController = requireActivity().findNavController(R.id.fragmentContainerView)
                navController.navigate(DailyTabLayoutDirections.actionDailyTabLayoutToFindCityFragment())
                return@observe
            }
            // Init TabLayout when find userCities
            var selectedTab: TabLayout.Tab? = null
            binding.mainWiewPager.adapter = TabsAdapter(requireActivity(), userCities)
            TabLayoutMediator(binding.tabLayout, binding.mainWiewPager) { tab, position ->
                tab.text = userCities.get(position).name
                if (tab.text == currentOpeningCity) selectedTab = tab
            }.attach()
            selectedTab?.select()
        })
    }

    inner class TabsAdapter(
        activity: FragmentActivity,
        private val tabs: List<CitiesUserSelectedDB>
    ) :
        FragmentStateAdapter(activity) {
        override fun getItemCount(): Int = tabs.size
        override fun createFragment(position: Int): Fragment {
            return DailyWeatherFragment.newInstance(tabs[position].city_id)
        }
    }
}

