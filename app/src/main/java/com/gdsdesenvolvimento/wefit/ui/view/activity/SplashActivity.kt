package com.gdsdesenvolvimento.wefit.ui.view.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.gdsdesenvolvimento.wefit.databinding.ActivitySplashBinding
import com.gdsdesenvolvimento.wefit.ui.view.base.BaseActivity
import com.gdsdesenvolvimento.wefit.util.constants.AppConstants
import com.gdsdesenvolvimento.wefit.util.extensions.nextScreen

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<ActivitySplashBinding>() {
    override fun activityBinding(): ActivitySplashBinding {
        return ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun code() {
        timeDelay{
            nextScreen(MainActivity())
        }
    }

    private fun timeDelay(function: () -> Unit) {
        Handler(Looper.myLooper()!!).postDelayed({
            function.invoke()
        },AppConstants.TIME_SPLASH)
    }

}