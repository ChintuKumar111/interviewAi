package com.example.intervuwai

import android.os.Bundle
import android.os.Looper
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postDelayed
import java.util.logging.Handler

class MainActivity : AppCompatActivity() {
    private var doubleBackToExitPressedOnce = false
    private lateinit var webView: WebView  // WebView reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.myWebView)

        // Enable JavaScript (optional but required for most sites)
        webView.settings.javaScriptEnabled = true

        // Open links inside WebView instead of browser
        webView.webViewClient = WebViewClient()

        // Load your website
        webView.loadUrl("https://inter-vue-ai-six.vercel.app/")
    }

    // Handle back press for WebView navigation
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

            android.os.Handler(Looper.getMainLooper()).postDelayed({
                doubleBackToExitPressedOnce = false
            }, 2000) // 2 seconds timeout
        }
    }
}