package com.yweber.crossfittimer.timer

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.CountDownTimer
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.yweber.crossfittimer.ui.UITimer
import kotlin.math.min

class CountDownTimer(millisInFuture: Long = 60_000, private val uiTimer: UITimer) : CountDownTimer(millisInFuture, 500) {
    var millisTimeRemaining: Long = 0
    override fun onFinish() {
    }

    override fun onTick(millisUntilFinished: Long) {
        updateTimer(millisUntilFinished)
        millisTimeRemaining = millisUntilFinished
    }

    private fun updateTimer(millisUntilFinished: Long) {

        val minutes : Int = (millisUntilFinished / 60_000).toInt()
        val seconds : Int = ((millisUntilFinished - minutes * 60_000 )/ 1_000).toInt()
        var doublePoints : Char = when (millisUntilFinished.rem(1000) in 0..600){
            true -> uiTimer.DOUBLE_BLACK_POINTS
            else -> uiTimer.DOUBLE_RED_POINTS
        }

        uiTimer.resfreshDisplay(
            uiTimer.LETTER_D,
            uiTimer.LETTER_N,
            uiTimer.DOUBLE_BLACK_POINTS,
            minutes / 10 ,
            minutes.rem(10),
            doublePoints,
            seconds / 10,
            seconds.rem(10) )
    }

}