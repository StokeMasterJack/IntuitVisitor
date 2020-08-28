package com.intuit.august2020.intuitvisitor.services

import android.app.IntentService
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

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

        // Broadcasting the Temperature
        val queue = Volley.newRequestQueue(this)

        val url = "https://api.openweathermap.org/data/2.5/weather?q=Mountain+View&appid=0685c4e8066b577d449babf619cf4ab4&units=imperial"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                val temperature: Float = response.getJSONObject("main").getDouble("temp").toFloat()
                val broadcastIntent = Intent(ACTION_WEATHER_ACQUIRED)
                broadcastIntent.putExtra(EXTRA_WEATHER_DATA, temperature)
                sendBroadcast(broadcastIntent)
            },
            Response.ErrorListener { error ->
                // TODO: Handle error
            }
        )
        queue.add(jsonObjectRequest)


    } // After onHandleIntent the service will be stopped


    // MAIN UI THREAD
    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        super.onDestroy()
    }

}
