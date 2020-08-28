package com.intuit.august2020.intuitvisitor.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.intuit.august2020.intuitvisitor.R
import kotlinx.android.synthetic.main.activity_info.*

class InfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

//        webview.loadUrl("https://www.intuit.com/")
        webview.loadUrl("file:///android_asset/index.html")
        webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
//                if (request?.url.host=="intuit.com") {
//                    return true
//                } else {
                      // create an implicit intent to the browser
//                    return false
//                }
                return super.shouldOverrideUrlLoading(view, request)
            }
        }
        webview.settings.javaScriptEnabled = true
//        webview.loadData("<h1>Hello from Kotlin</h1><marquee>Hello!</marquee>",
//        "text/html", "utf-8")
    }
}