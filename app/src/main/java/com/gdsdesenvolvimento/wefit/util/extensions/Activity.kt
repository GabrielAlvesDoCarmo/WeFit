package com.gdsdesenvolvimento.wefit.util.extensions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.gdsdesenvolvimento.wefit.util.constants.AppConstants

fun AppCompatActivity.nextScreen(activity: AppCompatActivity){
    Intent(this,activity::class.java).apply {
        startActivity(this)
    }
}