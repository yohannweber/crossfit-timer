package com.yweber.crossfittimer

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.CountDownTimer
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import kotlin.math.min

class CountDownTimer(millisInFuture: Long = 60_000, private val images : Array<ImageView>, baseContext: Context) : CountDownTimer(millisInFuture, 1000) {
    init{
        loadDrawables(baseContext)
    }

    lateinit var drawables : Array<Drawable?>
    var pointsPersistency : Long = 0

    private fun loadDrawables(baseContext: Context) {
        drawables = arrayOf(
            ContextCompat.getDrawable(baseContext, R.drawable.ic_zero),
            ContextCompat.getDrawable(baseContext, R.drawable.ic_one),
            ContextCompat.getDrawable(baseContext, R.drawable.ic_two),
            ContextCompat.getDrawable(baseContext, R.drawable.ic_three),
            ContextCompat.getDrawable(baseContext, R.drawable.ic_four),
            ContextCompat.getDrawable(baseContext, R.drawable.ic_five),
            ContextCompat.getDrawable(baseContext, R.drawable.ic_six),
            ContextCompat.getDrawable(baseContext, R.drawable.ic_seven),
            ContextCompat.getDrawable(baseContext, R.drawable.ic_eigth),
            ContextCompat.getDrawable(baseContext, R.drawable.ic_nine),
            ContextCompat.getDrawable(baseContext, R.drawable.ic_point),
            ContextCompat.getDrawable(baseContext, R.drawable.ic_no_point_))
    }

    override fun onFinish() {
    }

    override fun onTick(millisUntilFinished: Long) {
        updateTimer(millisUntilFinished)
    }

    private fun updateTimer(millisUntilFinished: Long) {
        val minutes : Int = (millisUntilFinished / 60_000).toInt()
        val seconds : Int = ((millisUntilFinished - minutes * 60_000 )/ 1000).toInt()

        if(millisUntilFinished.rem(1000) == 0.toLong())
            pointsPersistency = 0
        else
            pointsPersistency += 1


        val first = (minutes / 10)
        val second = minutes.rem(10)
        val third = (seconds / 10)
        val fourth = seconds.rem(10)
        images[0].setImageDrawable( getImage(first))
        images[1].setImageDrawable( getImage(second))
        images[2].setImageDrawable( getImage(third) )
        images[3].setImageDrawable( getImage(fourth))

        if(millisUntilFinished.rem(1000) in 0..600)
            images[4].setImageDrawable( getImage(11))
        else
            images[4].setImageDrawable( getImage(10))

    }

    private fun getImage(number : Int) : Drawable? = drawables[number]
}