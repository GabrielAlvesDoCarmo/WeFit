package com.gdsdesenvolvimento.wefit.ui.view.activity

import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.gdsdesenvolvimento.wefit.R
import com.gdsdesenvolvimento.wefit.databinding.ActivityMainBinding
import com.gdsdesenvolvimento.wefit.ui.view.base.BaseActivity
import com.gdsdesenvolvimento.wefit.ui.view.fragment.ModalBottomSheetFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.lang.reflect.Array.newInstance

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun activityBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun code() {
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
            val modalBottomSheetFragment: ModalBottomSheetFragment =
                ModalBottomSheetFragment.newInstance()
            modalBottomSheetFragment.show(
                supportFragmentManager,
                "add_photo_dialog_fragment"
            )
        }
    }

}