package com.intuit.august2020.intuitvisitor.activities

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.intuit.august2020.intuitvisitor.R
import com.intuit.august2020.intuitvisitor.services.WeatherService
import kotlinx.android.synthetic.main.activity_visitor.*

class VisitorActivity : AppCompatActivity() {

    val receiver = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, dataIntent: Intent?) {
            val temp = dataIntent?.getFloatExtra(WeatherService.EXTRA_WEATHER_DATA,
                -100F)
            text_temperature.text = "${temp} F"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visitor)

        val serviceIntent = Intent(this, WeatherService::class.java)
        startService(serviceIntent)
    }

    override fun onResume() {
        val intentFilter = IntentFilter(WeatherService.ACTION_WEATHER_ACQUIRED)
        registerReceiver(receiver, intentFilter)
        super.onResume()
    }

    override fun onPause() {
        unregisterReceiver(receiver)
        super.onPause()
    }
}