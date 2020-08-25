package com.intuit.august2020.intuitvisitor

import android.net.wifi.hotspot2.pps.Credential

class API {

    companion object {
        fun login(user: String, pass: String, callback: (Boolean)->Unit) {
            if (user=="admin" && pass=="1234") {
                callback(true)
            } else {
                callback(false)
            }
        }

    }

    fun instanceFun() {

    }

}