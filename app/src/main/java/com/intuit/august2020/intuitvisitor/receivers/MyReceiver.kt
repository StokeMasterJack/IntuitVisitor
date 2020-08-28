package com.intuit.august2020.intuitvisitor.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.intuit.august2020.intuitvisitor.services.WeatherService

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        context.startService(Intent(context, WeatherService::class.java))
    }
}
