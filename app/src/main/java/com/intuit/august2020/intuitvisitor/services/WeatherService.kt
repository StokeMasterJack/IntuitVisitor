package com.intuit.august2020.intuitvisitor.services

import android.app.IntentService
import android.content.Intent
import android.util.Log
import android.widget.Toast

class WeatherService : IntentService("Echo Service") {

    companion object {
        const val TAG = "EchoService"
        const val EXTRA_WEATHER_DATA = "com.intuit.august2020.intuitvisitor.echo"
        const val ACTION_WEATHER_ACQUIRED = "com.intuit.august2020.intuitvisitor.temperature"
    }

    // MAIN UI THREAD
    override fun onCreate() {
        Log.d(TAG, "onCreate")
        Toast.makeText(this, "onStart", Toast.LENGTH_LONG).show()
        super.onCreate()

    }

    // WORKER THREAD
    override fun onHandleIntent(incomingIntent: Intent?) {
//        Log.d(TAG, "onHandleIntent ${incomingIntent?.getStringExtra(ACTION_EXTRA_ECHO)}")

//        Toast.makeText(this, "Hello from the Service: ${incomingIntent?.getStringExtra(
//            ACTION_EXTRA_ECHO
//        )}", Toast.LENGTH_LONG).show()

        //TODO Go to the network
        // Broadcasting the Temperature
        val temperature: Float = 65F
        val broadcastIntent = Intent(ACTION_WEATHER_ACQUIRED)
        broadcastIntent.putExtra(EXTRA_WEATHER_DATA, temperature)
        sendBroadcast(broadcastIntent)

    } // After onHandleIntent the service will be stopped


    // MAIN UI THREAD
    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        super.onDestroy()
    }

}
