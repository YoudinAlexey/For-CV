package ua.youdin.weatherapp.ui.settings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ua.youdin.weatherapp.databinding.ItemDeleteListBinding

class DeleteListAdapter(val viewModel: SettingViewModel) : RecyclerView.Adapter<DeleteListAdapter.ViewHolder>() {
    var data = listOf<CitiesUserSelectedForDelete>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], viewModel)
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder private constructor(var binding: ItemDeleteListBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(citiesForDelete: CitiesUserSelectedForDelete, viewModel: SettingViewModel) {
            binding.citiesUserSelectedForDelete = citiesForDelete
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup):ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemDeleteListBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}
