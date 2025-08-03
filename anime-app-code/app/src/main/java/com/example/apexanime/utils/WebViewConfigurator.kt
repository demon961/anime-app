package com.example.apexanime.utils

import android.webkit.WebView
import android.webkit.WebViewClient
import android.webkit.WebChromeClient
import android.webkit.WebSettings

object WebViewConfigurator {
    private const val AD_BLOCKING_SCRIPT = """
        (function() {
            function hideAds() {
                const selectors = [
                    'iframe[src*="ads"]',
                    'iframe[src*="doubleclick"]',
                    'iframe[src*="googleadservices"]',
                    '.adsbygoogle',
                    '.ad-container',
                    '.advertisement',
                    '.ads',
                    '.popup',
                    '.modal[class*="ad"]',
                    'div[class*="ad-"]',
                    'div[class*="ads-"]',
                    'div[id*="ad-"]',
                    'div[id*="ads-"]'
                ];
                
                selectors.forEach(selector => {
                    const elements = document.querySelectorAll(selector);
                    elements.forEach(element => {
                        element.style.display = 'none';
                        element.remove();
                    });
                });
            }
            
            hideAds();
            
            const observer = new MutationObserver(hideAds);
            observer.observe(document.body, {
                childList: true,
                subtree: true
            });
        })();
    """

    fun configureWebView(webView: WebView) {
        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true
        webSettings.databaseEnabled = true
        webSettings.setAppCacheEnabled(true)
        webSettings.allowFileAccess = true
        webSettings.allowContentAccess = true
        webSettings.loadsImagesAutomatically = true
        webSettings.blockNetworkImage = false
        webSettings.blockNetworkLoads = false
        webSettings.cacheMode = WebSettings.LOAD_DEFAULT
        webSettings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        webSettings.userAgentString = webSettings.userAgentString + " ApexAnime/1.0"
    }

    fun injectAdBlockingScript(webView: WebView) {
        webView.evaluateJavascript(AD_BLOCKING_SCRIPT, null)
    }
} 