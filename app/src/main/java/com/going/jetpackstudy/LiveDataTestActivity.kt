package com.going.jetpackstudy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import kotlinx.android.synthetic.main.activity_livedata_test.*

/**
 * liveData 学习
 */
class LiveDataTestActivity : AppCompatActivity() {

    /**
     *  第一步 生命一个liveData
     */
    var liveData = MutableLiveData<String>()

    /**
     *  liveData.map 可以将数据生成map格式 ,liveData数据改变 相应的liveMapData的数据随之改变
     */
    val liveMapData:LiveData<Map<String,String>> =liveData.map {
        mapOf("it" to it.hashCode().toString())
    }

    /**
     *
     *当LiveData含有多个数据源时候 通过MediatoeLiveData的addSouse()作为中介者使用
     * 当当前activity或者fragment等观察者 处于不活跃状态时候,多个数据源(即多个LiveData)在此时发生变化,
     * 然后观察者重新返回活跃状态,MediatoeLiveData所获取到的数据为最后一个通过addSouse()添加进来的数据源(即LiveData)数据
     *
     */
    val oneData=MutableLiveData<String>()
    val twoData=MutableLiveData<String>()
    val mediatorLiveData=MediatorLiveData<Pair<String,String>>()


    val fragment = LiveDataFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_livedata_test)
        supportFragmentManager.beginTransaction().add(R.id.layout_content, fragment).commit()
        liveData.postValue("000000")

        /**
         * 在当前Activity观察
         *
         */
        liveData.observe(this, Observer {
            tv_activity_value.text="Activity观察得到: ${liveData.value}"
        })

        liveMapData.observe(this, Observer {
            tv_map_value.text= "ActivityMap观察得到: ${liveMapData.value?.get("it")}"
        })
        initListener()
    }

    private fun initListener() {
        btn_show.setOnClickListener {
            supportFragmentManager.beginTransaction().attach(fragment).commit()

        }
        btn_hide.setOnClickListener {
            supportFragmentManager.beginTransaction().detach(fragment).commit()
        }

        /**
         * 按钮每点击一次 改变一次liveData的值
         */
        btn_get_data.setOnClickListener {
            liveData.postValue("${System.currentTimeMillis()}")
        }
        btn_one.setOnClickListener {
            oneData.postValue("Oneeee${System.currentTimeMillis()}")

        }

        btn_two.setOnClickListener {
            twoData.postValue("Twoooo${System.currentTimeMillis()}")

        }

        mediatorLiveData.addSource(twoData, Observer {
            mediatorLiveData.value= "Two -" to it
        })
        mediatorLiveData.addSource(oneData, Observer {
            mediatorLiveData.value= "One -" to it
        })

    }


}