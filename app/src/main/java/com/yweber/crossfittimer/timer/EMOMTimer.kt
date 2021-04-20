package com.yweber.crossfittimer.timer

import android.os.CountDownTimer
import com.yweber.crossfittimer.ui.UITimer

class EMOMTimer(private val intervalNumber : Int, private val uiTimer: UITimer){

    var countDownTimer : CountUpTimer = CountUpTimer(uiTimer = uiTimer)
    private var intervalNumberCounter : Int = intervalNumber

    fun start(){
        countDownTimer = if( countDownTimer != null && countDownTimer.timeRemaining > 0)
            CountUpTimer(countDownTimer.timeRemaining, uiTimer)
        else
            CountUpTimer(uiTimer = uiTimer)

        countDownTimer.onFinished.add { timeRemaining -> onTimerFinished(timeRemaining) }
        countDownTimer.start()
    }
    fun pause(){
        countDownTimer.cancel()
    }

    fun cancel(){
        countDownTimer.timeRemaining = 0
        countDownTimer.cancel()
    }

    private fun onTimerFinished(timeRemaining : Long) {
        intervalNumberCounter--
        if(intervalNumberCounter > 0 ){
            countDownTimer = CountUpTimer(uiTimer = uiTimer)
            countDownTimer.onFinished.add{ timeRemaining -> onTimerFinished(timeRemaining)}
            countDownTimer.start()
        }
    }
}