package com.gdsdesenvolvimento.wefit.ui.view.activity

import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.gdsdesenvolvimento.wefit.R
import com.gdsdesenvolvimento.wefit.databinding.ActivityMainBinding
import com.gdsdesenvolvimento.wefit.ui.view.base.BaseActivity
import com.gdsdesenvolvimento.wefit.ui.view.fragment.ModalFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BaseActivity<ActivityMainBinding>() {
    private lateinit var navView: BottomNavigationView
    private lateinit var navController  : NavController

    override fun activityBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun code() {
        initNavigation()
        listeners()
    }

    private fun initNavigation() {
        navView = binding.bottomNavigationView
        navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)
    }

    private fun listeners() = with(binding) {
        included.btnSettings.setOnClickListener {
            val modalFragment: ModalFragment =
                ModalFragment.newInstance()
            modalFragment.show(
                supportFragmentManager,null
            )
        }
    }

}