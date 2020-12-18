package com.going.jetpackstudy.test

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


/**
 * ViewModel的创建,第一种方式,实现ViewModel抽象类
 */
class OneModel : ViewModel() {

    init {
        println("ViewModel  One Created")
    }

    fun getTime(): String {
        return "ViewModel One  的时间为${System.currentTimeMillis()}"
    }

    override fun onCleared() {
        super.onCleared()
        println("ViewModel  One onCleared")
    }

}