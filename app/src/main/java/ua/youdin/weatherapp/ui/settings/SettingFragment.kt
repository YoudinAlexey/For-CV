package ua.youdin.weatherapp.ui.settings

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import org.koin.androidx.viewmodel.ext.android.viewModel
import ua.youdin.weatherapp.R
import ua.youdin.weatherapp.databinding.SettingFragmentBinding
import ua.youdin.weatherapp.servise.EventObserver

class SettingFragment : Fragment(R.layout.setting_fragment) {
    private var _binding: SettingFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SettingViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SettingFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this.viewLifecycleOwner

        MobileAds.initialize(requireActivity()) {}
        val adRequest = AdRequest.Builder().build()
        binding.include2.adView.loadAd(adRequest)

        viewModel.getCities()
        val adapter = DeleteListAdapter(viewModel)
        binding.deleteList.adapter = adapter
        viewModel.citiesUserSelectedForDelete.observe(viewLifecycleOwner, EventObserver {
            it?.let {
                adapter.data = it
                Log.d("Tag", it.toString())
            }
        })
        return binding.root
    }


}