package com.going.jetpackstudy.test

import android.renderscript.Sampler
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import com.going.course.BR

//继承自BaseObservable的数据类,该类下的数据可以做到感知变化,比如user字段在变化时候,view展示也会随之改变
class UserData(name: String, val age: Int = 18, var address: String) : BaseObservable() {
    constructor() : this("张三", 2, "北京天安门")


    var name: String = name
        @Bindable
        get() {
            return field
        }
        //需要在set时候使用notifyPropertyChanged(该字段)来响应变化
        set(value) {
            notifyPropertyChanged(BR.name)
            field = value
        }


}
