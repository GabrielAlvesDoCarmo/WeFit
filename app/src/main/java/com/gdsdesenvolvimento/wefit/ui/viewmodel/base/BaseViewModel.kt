package com.gdsdesenvolvimento.wefit.ui.viewmodel.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel(){
    private val scope = CoroutineScope(Job()+Dispatchers.Main)
    fun <T>launcher(action : suspend () -> T){
        scope.launch {
            try {
                action.invoke()
            }catch (e : Exception){
                e.printStackTrace()
            }
        }
    }
}