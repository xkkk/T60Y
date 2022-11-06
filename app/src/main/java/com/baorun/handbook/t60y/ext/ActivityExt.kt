package com.baorun.handbook.t60y.ext

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity


fun Activity.goActivity(clazz: Class<out AppCompatActivity>,finish:Boolean = false){
    Intent(this,clazz).also {
       this.startActivity(it)
        if(finish){
            this.finish()
        }
    }
}

