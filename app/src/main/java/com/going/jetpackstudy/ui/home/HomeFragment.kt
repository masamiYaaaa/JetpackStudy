package com.going.jetpackstudy.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.going.jetpackstudy.test.LiveDataTestActivity
import com.going.jetpackstudy.R
import com.going.jetpackstudy.test.DataBindingTestActivity
import com.going.jetpackstudy.test.ViewModelTestActivity
import com.going.jetpackstudy.util.jump
import com.going.jetpackstudy.widget.SplashDanceView
import kotlin.random.Random

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: SplashDanceView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.setText(it)
        })
        textView.setOnClickListener {
//            jump<DataBindingTestActivity> {  }
            textView.setText("${(2..200).random()}")

        }
        return root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)


    }

}