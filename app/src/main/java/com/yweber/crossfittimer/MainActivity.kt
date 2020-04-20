package com.yweber.crossfittimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.WindowManager
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var countDownTimer : CountDownTimer
    var timerIsRunning : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get device dimensions
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)

        var width = displayMetrics.widthPixels / displayMetrics.density
        var height = displayMetrics.heightPixels /displayMetrics.density

        resizeImageView(displayMetrics, arrayOf(iv_first, iv_second, iv_third, iv_fourth, iv_points, iv_points, iv_prefix1,iv_prefix2))

        //textV.text = width.toString() + " x " +height.toString()
        countDownTimer = CountDownTimer(61_000, arrayOf(iv_first, iv_second, iv_third, iv_fourth, iv_points), baseContext)
        timerButton.setOnClickListener {
            if (!timerIsRunning)
                countDownTimer.start()
            else
                countDownTimer.cancel()

            timerIsRunning = !timerIsRunning

        }
    }

    fun resizeImageView( displayMetrics: DisplayMetrics, imagesView : Array<ImageView?>){
        var width = displayMetrics.widthPixels / 7
        var layoutParams = iv_first.layoutParams

        val sizingCoef = width / layoutParams.width
        val newWidth = width
        val newHeight = layoutParams.height * sizingCoef

        for (imageView in imagesView){
            layoutParams = imageView?.layoutParams
            layoutParams.width = newWidth.toInt()
            layoutParams.height = newHeight.toInt()
            imageView?.layoutParams= layoutParams
        }
    }

}
