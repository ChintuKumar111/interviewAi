package com.example.intervuwai

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private var doubleBackToExitPressedOnce = false
    private lateinit var loadingDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.myWebView)

        // Create a custom ProgressDialog
        loadingDialog = Dialog(this)
        loadingDialog.setContentView(R.layout.dialog_loading)
        loadingDialog.setCancelable(false) // user can’t dismiss
        loadingDialog.show()

        // Enable JavaScript
        webView.settings.javaScriptEnabled = true

        // Open links inside WebView & dismiss loader when page is finished
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                loadingDialog.dismiss() // hide when page is loaded
            }
        }

        // Load your website
        webView.loadUrl("https://inter-vue-ai-six.vercel.app/")
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed()
                return
            }

            doubleBackToExitPressedOnce = true
            Toast.makeText(this, "Press BACK again to exit", Toast.LENGTH_SHORT).show()

            Handler(Looper.getMainLooper()).postDelayed({
                doubleBackToExitPressedOnce = false
            }, 2000)
        }
    }
}
