package ua.youdin.weatherapp.ui.settings

import android.widget.CompoundButton
import androidx.databinding.BindingAdapter

@BindingAdapter(
    value = ["app:onCheckedForDelete", "app:viewmodelForDelete"],
    requireAll = true
)
fun onCheckedForDelete(
    view: CompoundButton,
    citiesUserSelectedForDelete: CitiesUserSelectedForDelete,
    viewModel: SettingViewModel
) {
    view.setOnCheckedChangeListener { _, isChecked ->
        if (isChecked) viewModel.deleteSelectedCity(citiesUserSelectedForDelete)
    }

}