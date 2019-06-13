package com.nsi.okexsay.ui.homepage;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nsi.okexsay.R;
import com.nsi.okexsay.ui.base.BaseActivity;

import java.net.URISyntaxException;

/**
 * html5页面
 * Created by GLX on 2017/5/16.
 */

public class Web_Activity extends BaseActivity {

    private ImageView fanhui;
    private WebView webView;

    private TextView id_title;
    private RelativeLayout id_tagbar;

    private ProgressBar progressBar;

    private String url;
    private String title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        title = intent.getStringExtra("title");

        Log.e("uuuuuuu", url);
        initView();
    }

    private void initView() {
        id_title = (TextView) findViewById(R.id.id_title);
        if (id_title != null) {
            id_title.setText(title);
        }

        id_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        id_tagbar = (RelativeLayout) findViewById(R.id.id_tagbar);
        if (title.equals("")) {
            id_tagbar.setVisibility(View.GONE);
        }

        progressBar = findViewById(R.id.progressBar);

        fanhui = (ImageView) findViewById(R.id.id_fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        webView = (WebView) findViewById(R.id.id_webview);
        WebSettings seting = webView.getSettings();
        seting.setDomStorageEnabled(true);
        seting.setUseWideViewPort(true);
        //设置支持访问文件
        seting.setAllowFileAccess(true);
        // 使用localStorage则必须打开
        seting.setGeolocationEnabled(true);
        //设置webview支持javascript脚本
        seting.setJavaScriptEnabled(true);
        //设置与JS交互
        webView.addJavascriptInterface(new JavaScriptinterface(this), "App");
        //设置缓存模式
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.loadUrl(url);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                try {
                    //如果是普通地址
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        view.loadUrl(request.getUrl().toString());
                    } else {
                        view.loadUrl(request.toString());
                    }
                } catch (Exception e) {
                    return false;
                }
                return true;
            }
        });


        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (shouldOverrideUrlLoadingByApp(view, url)) {
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, url);
            }

        });


        //检测进度
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);//加载完网页进度条消失
                } else {
                    progressBar.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    progressBar.setProgress(newProgress);//设置进度值
                }

            }
        });
    }


    /**
     * 根据url的scheme处理跳转第三方app的业务
     */
    private boolean shouldOverrideUrlLoadingByApp(WebView view, String url) {
        return shouldOverrideUrlLoadingByAppInternal(view, url, true);
    }

    private boolean shouldOverrideUrlLoadingByAppInternal(WebView view, String url, boolean interceptExternalProtocol) {
        if (isAcceptedScheme(url)) {
            //如果这个地址是浏览器可以处理的地址
            return false;
        }
        Intent intent;
        try {
            intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
        } catch (URISyntaxException e) {
            return interceptExternalProtocol;
        }

        intent.setComponent(null);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
            intent.setSelector(null);
        }

        //该Intent无法被设备上的应用程序处理
        if (Web_Activity.this.getPackageManager().resolveActivity(intent, 0) == null) {
            return tryHandleByMarket(intent) || interceptExternalProtocol;
        }

        try {
            Web_Activity.this.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            return interceptExternalProtocol;
        }
        return true;
    }


    private boolean tryHandleByMarket(Intent intent) {
        String packagename = intent.getPackage();
        if (packagename != null) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packagename));
            try {
                Web_Activity.this.startActivity(intent);
            } catch (ActivityNotFoundException e) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }


    /**
     * 该url是否属于浏览器能处理的内部协议
     */
    private boolean isAcceptedScheme(String url) {
        if (Patterns.WEB_URL.matcher(url).matches() || URLUtil.isValidUrl(url)) {
            return true;
        } else {
            return false;
        }
    }


    public class JavaScriptinterface {
        Context context;

        public JavaScriptinterface(Context c) {
            context = c;
        }

        /**
         * 与js交互时用到的方法，在js里直接调用的
         * 抽奖完成后调用
         */
        @JavascriptInterface
        public void showDialog(String tex) {

        }

        /*
         * JS调用android的方法
         * @JavascriptInterface仍然必不可少
         * */
        @JavascriptInterface
        public void backApp() {
            finish();
        }
    }


}
