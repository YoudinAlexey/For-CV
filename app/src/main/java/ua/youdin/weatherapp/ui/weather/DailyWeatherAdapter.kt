package ua.youdin.weatherapp.ui.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ua.youdin.weatherapp.data.sourse.localSqlApi.tables.DailyWeatherDB
import ua.youdin.weatherapp.databinding.ItemDailyWeatherBinding


class DailyWeatherAdapter(
    private val viewModel: DailyWeatherViewModel,
    private val temperature_units: Boolean,
    private val interface_language: String
) :
    ListAdapter<DailyWeatherDB, DailyWeatherAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(viewModel, item, temperature_units, interface_language)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ItemDailyWeatherBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            viewModel: DailyWeatherViewModel,
            item: DailyWeatherDB,
            temperature_units: Boolean,
            interface_language: String
        ) {
            binding.viewmodel = viewModel
            binding.day = item
            binding.temperatureUnits = temperature_units
            binding.interfaceLanguage = interface_language
            binding.executePendingBindings()
        }


        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemDailyWeatherBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DailyWeatherDB>() {
            override fun areItemsTheSame(
                oldItem: DailyWeatherDB,
                newItem: DailyWeatherDB,
            ): Boolean {
                return oldItem.dt == oldItem.dt
            }

            override fun areContentsTheSame(
                oldItem: DailyWeatherDB,
                newItem: DailyWeatherDB,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
