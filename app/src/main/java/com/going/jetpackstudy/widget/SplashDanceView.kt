package com.going.jetpackstudy.widget

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.Log
import android.util.SparseArray
import android.view.View
import android.view.WindowManager
import com.going.jetpackstudy.R
import java.security.cert.Extension

//跳舞的闪屏
class SplashDanceView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var logoName: String
    private var logoTextSize: Float
    private var logoTextPadding: Float
    private var textColor: Int
    private var lightColor: Int
    lateinit var paint: Paint
    private var screenWidth: Int

    //存放公司名字的单个字符
    private val charList = mutableListOf<String>()

    //创建两个数组来存放打乱后和最终的字符坐标
    private val quietPointF = SparseArray<String>()
    private val randomPointF = SparseArray<String>()

    //宽高
    private var mWidth: Int = 0
    private var mHeight: Int = 0


    init {
        val ta: TypedArray = context!!.obtainStyledAttributes(attrs, R.styleable.SplashDance)
        logoName = ta.getString(R.styleable.SplashDance_logoTextName)!!
        logoTextSize = ta.getDimension(R.styleable.SplashDance_logoTextSize, 20f)!!
        logoTextPadding = ta.getDimension(R.styleable.SplashDance_logoTextPadding, 1f)!!
        textColor = ta.getColor(R.styleable.SplashDance_logoTextColor, Color.BLACK)
        lightColor = ta.getColor(R.styleable.SplashDance_logoLightColor, Color.WHITE)


        ta.recycle()
        initCharList()
        initPaint()
        val metrics = DisplayMetrics();
        screenWidth = metrics.widthPixels;            //屏幕宽度

    }

    override fun onDraw(canvas: Canvas?) {
        val rectF = RectF()
        rectF.set(200f, 200f, 600f, 400f)
        paint.color = Color.CYAN
        canvas!!.drawRect(rectF, paint)
        val paitFontMetrics = paint.fontMetrics
        paint.color = textColor
        canvas.width
        canvas!!.drawText(
            logoName,
            mWidth.toFloat() / 2,
            ((paitFontMetrics.bottom - paitFontMetrics.top) / 2 - paitFontMetrics.bottom + mHeight) / 2,
            paint
        )
    }

    fun initPaint() {
        paint = Paint()
        paint.isAntiAlias = true
        paint.color = textColor
        paint.textSize = logoTextSize
        paint.textAlign = Paint.Align.CENTER

    }

    //onSizeChanged 后将字符串随机打乱在屏幕上
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHeight = h
        setScatterChar()

    }

    //随机散布单个字符
    private fun setScatterChar() {
        if (mWidth == 0 || mHeight == 0) {
            Log.w(this::class.java.simpleName, "The view has not measure, it will auto init later.")
            return
        }
        //y轴中心点
        val mCenterY = (mHeight + paint.textSize) / 2
        //计算view最终的长度:宽度+padding

        var totalLength = 0f
        for ((index, value) in charList.withIndex()) {
            paint.measureText(value)
            totalLength += if (index == charList.size - 1) totalLength else totalLength + logoTextPadding
        }
        //如果长度超出宽度抛异常
        if (totalLength > width) {
            throw IllegalAccessException("the totalLength must small than zhe danceView width")
        }
        //从左至右开始获取随机坐标


    }


    fun setText(str: String) {
        logoName = str
        invalidate()
    }

    private fun initCharList() {
        logoName?.apply {
            logoName.toCharArray().forEach {
                charList.add(it.toString())
            }
        }
    }


}