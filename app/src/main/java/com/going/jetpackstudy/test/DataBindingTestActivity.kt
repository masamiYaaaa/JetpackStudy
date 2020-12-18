package com.going.jetpackstudy.test

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.going.jetpackstudy.R
import com.going.jetpackstudy.databinding.ActivityDatabindingTestBinding

class DataBindingTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityDatabindingTestBinding>(
            this,
            R.layout.activity_databinding_test
        )
        val userModel: UserModel by viewModels { defaultViewModelProviderFactory }

        var userData = UserData("李四", 23, "日本富士山")
//        user.name = null

        binding.apply {
            user = userData
            changeUserData = View.OnClickListener {
                user?.name = "刻晴"
            }

        }

    }


}