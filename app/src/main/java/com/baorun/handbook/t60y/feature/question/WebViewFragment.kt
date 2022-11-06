package com.baorun.handbook.t60y.feature.question

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.baorun.handbook.t60y.KEY_BUNDLE_URL
import com.baorun.handbook.t60y.databinding.FragmentWebviewBinding

/**
 * 功能：
 * 描述：
 * Created by xukun on 2021/8/20.
 */
@SuppressLint("SetJavaScriptEnabled")
class WebViewFragment:Fragment() {

    private lateinit var viewBinding: FragmentWebviewBinding

    private val url:String by lazy {
        requireArguments().getString(KEY_BUNDLE_URL,"")
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentWebviewBinding.inflate(inflater)
        return viewBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.webView.settings.javaScriptEnabled = true
        viewBinding.webView.settings.javaScriptCanOpenWindowsAutomatically = true
        viewBinding.webView.settings.cacheMode = WebSettings.LOAD_NO_CACHE

        viewBinding.webView.webChromeClient = WebChromeClient()
        viewBinding.webView.webViewClient = WebViewClient()
//        viewBinding.webView.webViewClient = object :WebViewClient(){
//            override fun onPageFinished(view: WebView?, url: String?) {
//                super.onPageFinished(view, url)
//                val js = getDataJson(AppContext,"public/js/jquery.lazyload.js")
//                view?.evaluateJavascript(js?:"",object:ValueCallback<String>{
//                    override fun onReceiveValue(value: String?) {
//                        LogUtils.i(value)
//                    }
//
//                })
//            }
//        }
        viewBinding.webView.settings.allowContentAccess = true
        viewBinding.webView.settings.builtInZoomControls = true
        viewBinding.webView.settings.useWideViewPort = true
        viewBinding.webView.settings.loadWithOverviewMode = true
        viewBinding.webView.settings.domStorageEnabled = true

        viewBinding.webView.setBackgroundColor(0)
        viewBinding.webView.loadUrl(url)
        viewBinding.webView.requestFocus()

    }


    companion object{
        fun newInstance( url:String): WebViewFragment {
            return WebViewFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_BUNDLE_URL,url)
                }
            }
        }
    }
}