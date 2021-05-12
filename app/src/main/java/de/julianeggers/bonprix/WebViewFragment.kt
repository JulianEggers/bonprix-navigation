package de.julianeggers.bonprix

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_webview.*

private const val ARG_URL = "url"

/**
 * [Fragment] that is used to display webpages in a WebView.
 * It displays the given webpage or the 404 webpage of bonprix.
 */
class WebViewFragment : Fragment() {
    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            url = it.getString(ARG_URL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_webview, container, false)
        return view
    }

    override fun onStart() {
        super.onStart()
        web_view.settings.javaScriptEnabled = true
        web_view.loadUrl(url ?: "https://www.bonprix.de/404/")
    }

    companion object {
        /**
         * Creates a new [WebViewFragment] that displays the [paramUrl]
         * in a WebView or the 404 webpage of bonprix in case no [paramUrl] is set.
         */
        fun newInstance(paramUrl: String?) =
            WebViewFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_URL, paramUrl)
                }
            }
    }
}