package com.going.jetpackstudy.test

import android.app.Application
import androidx.lifecycle.AndroidViewModel


/**
 *
 * ViewModel的第二种创建方式 ,当需要使用到Application时候 可以继承AndroidViewModel
 */
class TwoModel(application: Application):AndroidViewModel(application) {
    init {
        println("ViewModel Two Created")
    }

    internal fun getTime():String{
        return "ViewModel Two 获取到的时间为${System.currentTimeMillis()}"
    }

    override fun onCleared() {
        super.onCleared()
        println("ViewModel Two Cleared")
    }


}