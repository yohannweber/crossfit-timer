package com.yweber.crossfittimer.timer

import android.os.CountDownTimer
import com.yweber.crossfittimer.ui.UITimer

class CountUpTimer(val millisInFuture: Long = 10_000, private val uiTimer: UITimer) : CountDownTimer(millisInFuture, 500) {

    var timeRemaining : Long = 0
    val onFinished = mutableListOf<(Long) -> Unit>()

    override fun onFinish() {
        onFinished.forEach{it(timeRemaining)}
    }

    override fun onTick(millisUntilFinished: Long) {
        timeRemaining = millisUntilFinished
        updateTimer(millisUntilFinished)
    }

    private fun updateTimer(millisUntilFinished: Long) {

        val minutes : Int = ((millisInFuture - millisUntilFinished) / 60_000).toInt()
        val seconds : Int = (((millisInFuture - millisUntilFinished)- minutes * 60_000 )/ 1_000).toInt()
        var doublePoints : Char = when (millisUntilFinished.rem(1000) in 0..600){
            true -> uiTimer.DOUBLE_BLACK_POINTS
            else -> uiTimer.DOUBLE_RED_POINTS
        }

        uiTimer.resfreshDisplay(
            uiTimer.LETTER_U,
            uiTimer.LETTER_P,
            uiTimer.DOUBLE_BLACK_POINTS,
            minutes / 10 ,
            minutes.rem(10),
            doublePoints,
            seconds / 10,
            seconds.rem(10) )
    }

}