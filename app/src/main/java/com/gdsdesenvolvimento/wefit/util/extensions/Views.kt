package com.gdsdesenvolvimento.wefit.util.extensions

import android.view.View

fun View.visibility(type : String){
    when(type){
        "i"->{
            this.visibility = View.INVISIBLE
        }
        "v"->{
            this.visibility = View.VISIBLE
        }
        "g"->{
            this.visibility = View.GONE
        }
    }
}