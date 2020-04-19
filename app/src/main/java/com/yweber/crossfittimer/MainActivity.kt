package com.yweber.crossfittimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var countDownTimer : CountDownTimer
    var timerIsRunning : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        countDownTimer = CountDownTimer(60_000, arrayOf(iv_first, iv_second, iv_third, iv_fourth, iv_points), baseContext)
        timerButton.setOnClickListener {
            if (!timerIsRunning)
                countDownTimer.start()
            else
                countDownTimer.cancel()

            timerIsRunning = !timerIsRunning

        }
    }

}
