package com.going.jetpackstudy.test

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.going.jetpackstudy.R
import kotlinx.android.synthetic.main.activity_viewmodel_tes.*

//ViewModel学习类


class ViewModelTestActivity : AppCompatActivity() {

    val oneModel: OneModel by viewModels { defaultViewModelProviderFactory }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_viewmodel_tes)
        btn_test.setOnClickListener {
            Toast.makeText(this, oneModel.getTime(), Toast.LENGTH_SHORT).show()
        }
        val twoModel: TwoModel by viewModels { defaultViewModelProviderFactory }
        twoModel.getTime()
    }


}
