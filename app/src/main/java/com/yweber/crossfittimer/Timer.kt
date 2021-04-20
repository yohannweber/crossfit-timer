package com.yweber.crossfittimer


import android.os.Bundle
import android.util.DisplayMetrics
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.yweber.crossfittimer.timer.CountDownTimer
import com.yweber.crossfittimer.timer.CountUpTimer
import com.yweber.crossfittimer.timer.EMOMTimer
import com.yweber.crossfittimer.ui.UITimer
import kotlinx.android.synthetic.main.fragment_timer.*

/**
 * A simple [Fragment] subclass.
 */
class Timer : Fragment() {

    //lateinit var countDownTimer : CountDownTimer
    lateinit var countDownTimer : EMOMTimer
    lateinit var uiTimer: UITimer
    var timerIsRunning : Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_timer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // get device dimensions
        val activityFragment = activity
        if(activityFragment != null){
            val displayMetrics = DisplayMetrics()
            activityFragment.windowManager.defaultDisplay.getMetrics(displayMetrics)

            var width = displayMetrics.widthPixels / displayMetrics.density
            var height = displayMetrics.heightPixels / displayMetrics.density

            resizeImageView(
                displayMetrics,
                iv_hour1, iv_hour2, iv_doublePoint1, iv_minute1, iv_minute2, iv_doublePoints2, iv_second1, iv_second2
            )

            uiTimer = UITimer(activityFragment.baseContext,
                arrayOf(iv_hour1, iv_hour2, iv_doublePoint1, iv_minute1, iv_minute2, iv_doublePoints2, iv_second1, iv_second2))

            countDownTimer = EMOMTimer(3,
                uiTimer
            )
            timerButton.setOnClickListener {
                if (!timerIsRunning)
                    countDownTimer.start()
                else
                    countDownTimer.pause()

                timerIsRunning = !timerIsRunning

            }
        }

    }

    private fun resizeImageView(displayMetrics: DisplayMetrics, vararg imageViews: ImageView){
        /* var width : Float = when( resources.configuration.orientation) {
             ORIENTATION_LANDSCAPE -> displayMetrics.widthPixels
             else -> displayMetrics.heightPixels
         }.toFloat()*/


        var width : Float = displayMetrics.widthPixels.toFloat()
        width /= imageViews.size

        for (imageView in imageViews){

            imageView.let{
                val layoutParams = it.layoutParams
                val sizingCoef : Float = width / ( it.width )
                var newWidth = width
                var newHeight = it.layoutParams.height * sizingCoef

                layoutParams.width = newWidth.toInt()
                layoutParams.height = newHeight.toInt()
                it.layoutParams = layoutParams
            }
        }
    }

}
