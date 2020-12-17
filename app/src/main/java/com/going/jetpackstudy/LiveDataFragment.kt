package com.going.jetpackstudy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.map
import kotlinx.android.synthetic.main.fragment_livedata.*

class LiveDataFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_livedata, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_value.text = "Fragment初始化"
        tv_map_value.text = "Fragmentmap初始化"

        (requireActivity() as LiveDataTestActivity).apply {
            liveData.observe(viewLifecycleOwner, Observer {
                tv_value.text = "Fragment观察得到: ${liveData.value}"
            })
            val liveMappData: LiveData<Map<String, String>> = liveData.map {
                mapOf("it" to "aa${it.hashCode().toString()[5]}")
            }

            liveMappData.observe(viewLifecycleOwner, Observer {
                tv_map_value.text = "${Thread.currentThread()}FragmentMap观察得到: ${liveMappData.value?.get("it")}"
            })
            mediatorLiveData.observe(viewLifecycleOwner, Observer {
                tv_media_value.text=it.second
            })
        }
        tv_map_value.setOnClickListener {
            tv_map_value.text = "Adwad"
        }

    }
}