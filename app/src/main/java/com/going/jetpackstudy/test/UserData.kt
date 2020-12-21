package com.going.jetpackstudy.test

import android.graphics.Color
import android.renderscript.Sampler
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import com.going.course.BR
import kotlin.random.Random

//继承自BaseObservable的数据类,该类下的数据可以做到感知变化,比如user字段在变化时候,view展示也会随之改变
class UserData(name: String, val age: Int = 18, address: String, nameColor: Int) :
    BaseObservable() {
    constructor() : this("张三", 2, "北京天安门", Color.BLUE)


    var name: String? = name
        @Bindable
        get() {
            return field
        }
        //需要在set时候使用notifyPropertyChanged(该字段)来响应变化
        set(value) {
            notifyPropertyChanged(BR.name)
            field = value
        }


    var address: String = address
        @Bindable
        get() {
            return field
        }
        set(value) {
            notifyPropertyChanged(BR.address)
            field = value
        }

    var nameColor: Int = nameColor
        get() {
            val red = (0..255).random()
            val green = (0..255).random()
            val blue = (0..255).random()
            return Color.rgb(red, green, blue)
        }

}
