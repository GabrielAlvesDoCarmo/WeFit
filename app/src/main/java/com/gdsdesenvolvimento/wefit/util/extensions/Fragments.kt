package com.gdsdesenvolvimento.wefit.util.extensions

import android.app.AlertDialog
import androidx.fragment.app.Fragment

fun Fragment.dialog(title: String, msg: String, cancelable: Boolean, action: () -> Unit) {
    AlertDialog.Builder(requireContext())
        .setTitle(title)
        .setMessage(msg)
        .setCancelable(cancelable)
        .setPositiveButton("OK") { _, _ ->
            action.invoke()
        }.show()
}