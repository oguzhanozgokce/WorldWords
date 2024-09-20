package com.oguzhanozgokce.worldwords.common

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.text.Editable
import android.text.SpannableString
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.oguzhanozgokce.worldwords.R
import com.oguzhanozgokce.worldwords.model.Word

fun View.invisible(){
    this.visibility = View.INVISIBLE
}

fun View.gone(){
    this.visibility = View.GONE
}

fun View.visible(){
    this.visibility = View.VISIBLE
}

fun Fragment.navigateTo(action: NavDirections) {
    findNavController().navigate(action)
}

fun Fragment.showCustomAlertDialog(
    title: String,
    message: String,
    positiveButtonText: String,
    onPositiveAction: (() -> Unit)? = null,
    negativeButtonText: String? = null,
    onNegativeAction: (() -> Unit)? = null
) {
    val dialogBuilder = AlertDialog.Builder(requireContext())
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(positiveButtonText) { dialogInterface, _ ->
            onPositiveAction?.invoke()
            dialogInterface.dismiss()
        }

    negativeButtonText?.let {
        dialogBuilder.setNegativeButton(it) { dialogInterface, _ ->
            onNegativeAction?.invoke()
            dialogInterface.dismiss()
        }
    }

    val dialog = dialogBuilder.create()
    dialog.window?.setBackgroundDrawableResource(R.drawable.custom_dialog_background)
    dialog.show()
}

fun ImageView.loadImage(url: String?, placeholder: Int = R.drawable.ic_words, errorImage: Int = R.drawable.ic_bag) {
    Glide.with(this.context)
        .load(url)
        .placeholder(placeholder)
        .error(errorImage)
        .into(this)
}

fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun SharedPreferences.saveList(key: String, gson: Gson, words: List<Word>) {
    val editor = this.edit()
    val json = gson.toJson(words)
    editor.putString(key, json)
    editor.apply()
}

inline fun <reified T> SharedPreferences.getList(key: String, gson: Gson): List<T> {
    val json = this.getString(key, null)
    return if (json != null) {
        val type = object : TypeToken<List<T>>() {}.type
        gson.fromJson(json, type)
    } else {
        emptyList()
    }
}

fun EditText.setOnClearClickListener(onClear: () -> Unit) {
    setOnTouchListener { _, event ->
        val clearDrawable = compoundDrawables[2]
        if (clearDrawable != null && event.action == MotionEvent.ACTION_UP) {
            if (event.rawX >= (right - clearDrawable.bounds.width())) {
                onClear()
                performClick()
                return@setOnTouchListener true
            }
        }
        false
    }
}

fun EditText.updateSearchIconVisibility(text: Editable?) {
    if (text.isNullOrEmpty()) {
        setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_search, 0, 0, 0)
    } else {
        setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_search, 0, R.drawable.ic_clear, 0)
    }
}

fun EditText.addSimpleTextWatcher(afterTextChanged: (Editable?) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable?) {
            afterTextChanged(s)
        }
    })
}







