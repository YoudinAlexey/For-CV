package ua.youdin.weatherapp.servise

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment


fun Fragment.hideKeyboard(view: View) {
    val imm =
        this.requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Activity.hideKeyboard() {
    val imm: InputMethodManager =
        this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    var currentFocus = this.currentFocus
    if (currentFocus == null) {
        currentFocus = View(this)
    }
    imm.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0)
}