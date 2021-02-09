package ua.youdin.weatherapp.ui.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ua.youdin.weatherapp.data.sourse.localSqlApi.tables.HourlyWeatherDB
import ua.youdin.weatherapp.databinding.ItemHourlyWeatherBinding

class HourlyWeatherAdapter(
    private val viewModel: HourlyWeatherViewModel,
    private val temperature_units: Boolean,
    private val interface_language: String
) :
    ListAdapter<HourlyWeatherDB, HourlyWeatherAdapter.ViewHolder>(DIFF_CALLBACK) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(viewModel, item, temperature_units, interface_language)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ItemHourlyWeatherBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            viewModel: HourlyWeatherViewModel,
            item: HourlyWeatherDB,
            temperature_units: Boolean,
            interface_language: String,
        ) {
            binding.viewmodel = viewModel
            binding.hour = item
            binding.temperatureUnits = temperature_units
            binding.interfaceLanguage = interface_language
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemHourlyWeatherBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<HourlyWeatherDB>() {
            override fun areItemsTheSame(
                oldItem: HourlyWeatherDB,
                newItem: HourlyWeatherDB,
            ): Boolean {
                return oldItem.dt == oldItem.dt
            }

            override fun areContentsTheSame(
                oldItem: HourlyWeatherDB,
                newItem: HourlyWeatherDB,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}