package com.yweber.crossfittimer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCenter.start(
            application, "1870392f-a435-47f8-9424-a7f201ed5733",
            Analytics::class.java, Crashes::class.java
        )
        setContentView(R.layout.activity_main)
    }

}
