package com.example.apexanime

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.example.apexanime.constants.AdMobConstants
import com.example.apexanime.databinding.ActivityMainBinding
import com.example.apexanime.utils.WebViewConfigurator
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar
    private lateinit var adView: com.google.android.gms.ads.AdView
    
    private val targetUrl: String = "https://hianime.to/home"
    private var canGoBack: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        initializeViews()
        initializeAdMob()
        setupWebView()
        loadTargetUrl()
    }

    private fun initializeViews() {
        webView = binding.webView
        progressBar = binding.progressBar
        adView = binding.adView
    }

    private fun initializeAdMob() {
        MobileAds.initialize(this) {}
        val adRequest: AdRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
    }

    private fun setupWebView() {
        WebViewConfigurator.configureWebView(webView)
        
        webView.webViewClient = createWebViewClient()
        webView.webChromeClient = createWebChromeClient()
    }

    private fun createWebViewClient(): WebViewClient {
        return object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                WebViewConfigurator.injectAdBlockingScript(view)
                progressBar.visibility = View.GONE
            }

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        }
    }

    private fun createWebChromeClient(): WebChromeClient {
        return object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                if (newProgress < 100) {
                    progressBar.visibility = View.VISIBLE
                    progressBar.progress = newProgress
                } else {
                    progressBar.visibility = View.GONE
                }
            }
        }
    }

    private fun loadTargetUrl() {
        webView.loadUrl(targetUrl)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onPause() {
        super.onPause()
        webView.onPause()
        adView.pause()
    }

    override fun onResume() {
        super.onResume()
        webView.onResume()
        adView.resume()
    }

    override fun onDestroy() {
        super.onDestroy()
        webView.destroy()
        adView.destroy()
    }
}