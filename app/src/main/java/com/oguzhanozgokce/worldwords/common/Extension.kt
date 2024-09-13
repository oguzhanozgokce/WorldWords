package com.oguzhanozgokce.worldwords.common

import android.app.AlertDialog
import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.oguzhanozgokce.worldwords.R

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



