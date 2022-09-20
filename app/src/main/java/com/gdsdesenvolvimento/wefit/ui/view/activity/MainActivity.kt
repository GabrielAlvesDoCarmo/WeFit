package com.gdsdesenvolvimento.wefit.ui.view.activity

import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.gdsdesenvolvimento.wefit.R
import com.gdsdesenvolvimento.wefit.databinding.ActivityMainBinding
import com.gdsdesenvolvimento.wefit.di.AppInjection
import com.gdsdesenvolvimento.wefit.ui.view.base.BaseWithViewModelActivity
import com.gdsdesenvolvimento.wefit.ui.viewmodel.activity.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BaseWithViewModelActivity<ActivityMainBinding,MainViewModel>() {
    override fun activityBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun activityViewModel(): MainViewModel {
        return AppInjection.mainViewModel(this)
    }

    override fun codeInjection() {
        initNavigation()
        listeners()
    }

    private fun initNavigation() {
        val navView : BottomNavigationView = binding.bottomNavigationView
        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)
    }

    private fun listeners() = with(binding) {
        included.btnSettings.setOnClickListener {
            //TODO(colocar o clique na budega)
        }

    }

}