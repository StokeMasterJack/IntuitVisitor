package com.intuit.august2020.intuitvisitor.activities

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.webkit.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.intuit.august2020.intuitvisitor.R
import kotlinx.android.synthetic.main.activity_info.*


class InfoActivity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        MenuInflater(this).inflate(R.menu.info, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_about -> {
                val url = "https://intuit.com"
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                startActivity(intent)
            }
            R.id.action_help -> {
                webview.loadUrl("file:///android_asset/index.html")
            }
            R.id.action_location -> {
                webview.loadUrl("https://maps.google.com/?q=Intuit+Campus")

            }
            R.id.action_js -> {
                webview.evaluateJavascript(
                    """
                receiveMessage('Hello from Kotlin');
            """)
                {
                    Log.d("JS", it)
                }
            }
        }




        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // Chromium-webview
            WebView.setWebContentsDebuggingEnabled(true);
        }

        webview.addJavascriptInterface(object {
            @JavascriptInterface fun goBack() {
                finish()
            }
        }, "kotlin")

        webview.loadUrl("file:///android_asset/index.html")
        webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return super.shouldOverrideUrlLoading(view, request)
            }
        }
        webview.webChromeClient = object : WebChromeClient() {


            override fun onJsAlert(
                view: WebView?,
                url: String?,
                message: String?,
                result: JsResult?
            ): Boolean {
                Toast.makeText(this@InfoActivity, message, Toast.LENGTH_LONG).show()
                return false
            }
        }
        webview.settings.javaScriptEnabled = true
    }
}