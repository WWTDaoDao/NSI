/*
 * Copyright (C) 2015 pengjianbo(pengjianbosoft@gmail.com), Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package cn.finalteam.okhttpfinal;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import cn.finalteam.toolsfinal.JsonFormatUtils;
import cn.finalteam.toolsfinal.JsonValidator;
import cn.finalteam.toolsfinal.StringUtils;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.ref.WeakReference;
import java.net.SocketTimeoutException;
import java.util.Iterator;
import java.util.Map;

/**
 * Desction:Http请求Task
 * Author:pengjianbo
 * Date:15/7/3 上午11:14
 */
public class HttpTask extends AsyncTask<Void, Void, ResponseData> {

    public static final String DEFAULT_HTTP_TASK_KEY = "default_http_task_key";

    private static final int CALLBACK_SUCCESSFUL = 0x01;
    private static final int CALLBACK_FAILED = 0x02;
    public static final int ERROR_RESPONSE_UNKNOWN = 0x03;
    public static final int ERROR_RESPONSE_TIMEOUT = 0x04;

    public static final int RESPONSE_START = 0x10;
    public static final int RESPONSE_FINISH = 0x11;
    public static final int CODE_REE = 0x10;
    private String url;
    private RequestParams params;
    public BaseHttpRequestCallback callback;
    private int timeout;
    private Headers headers;
    private String requestKey;
    private String method;
    private Handler mHandler;
    public HttpTask(String method, String url, RequestParams params, BaseHttpRequestCallback callback, int timeout) {
        this.method = method;
        this.url = url;
        this.params = params;
        this.callback = callback;
        this.timeout = timeout;

        this.requestKey = params.getHttpTaskKey();
        if (StringUtils.isEmpty(requestKey)) {
            requestKey = DEFAULT_HTTP_TASK_KEY;
        }
        mHandler = new UIHandler(callback);
        //将请求的URL及参数组合成一个唯一请求
        HttpTaskHandler.getInstance().addTask(this.requestKey, this);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (params.headerMap != null) {
            headers = Headers.of(params.headerMap);
        }

        if (callback != null) {
            callback.onStart();
//            Message message=Message.obtain();
//            message.what=RESPONSE_START;
//            mHandler.sendMessage(message);
        }
    }

    @Override
    protected ResponseData doInBackground(Void... voids) {
        OkHttpClient client = OkHttpFactory.getOkHttpClientFactory(timeout);
        final ResponseData responseData = new ResponseData();

        //构建请求Request实例
        Request.Builder builder = new Request.Builder();
        builder.url(url).headers(headers);
        if (TextUtils.equals(method, "POST")) {
            RequestBody body = params.getRequestBody();
            builder.post(body);
        } else {
            Map<String, String> paramsMap = params.getUrlParams();
            StringBuffer urlFull = new StringBuffer();
            urlFull.append(url);
            if (urlFull.indexOf("?", 0) < 0 && paramsMap.size() > 0) {
                urlFull.append("?");
            }
            Iterator<Map.Entry<String, String>> paramsIterator = paramsMap.entrySet().iterator();
            while (paramsIterator.hasNext()) {
                Map.Entry<String, String> entry = paramsIterator.next();
                String key = entry.getKey();
                String value = entry.getValue();

                urlFull.append(key).append("=").append(value);
                if (paramsIterator.hasNext()) {
                    urlFull.append("&");
                }
            }
            url = urlFull.toString();
            builder.get();
        }
        Request request = builder.build();
        if (Constants.DEBUG) {
            Log.d("HttpTask", "url=" + url + "?" + params.toString());
        }
//        执行请求
//        Response response = null;
//        try {
//            response = client.newCall(request).execute();
//        } catch (Exception e) {
//            if (e instanceof SocketTimeoutException) {
//                responseData.setTimeout(true);
//            } else if (e instanceof InterruptedIOException && TextUtils.equals(e.getMessage(),
//                    "timeout")) {
//                responseData.setTimeout(true);
//            }
//        }
//
////        获取请求结果
//        if (response != null) {
//            responseData.setResponseNull(false);
//            responseData.setCode(response.code());
//            responseData.setMessage(response.message());
//            responseData.setSuccess(response.isSuccessful());
//            String respBody = "";
//            try {
//                respBody = response.body().string();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            responseData.setResponse(respBody);
//            responseData.setHeaders(response.headers());
//
//        } else {
//            responseData.setResponseNull(true);
//        }

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                if (e instanceof SocketTimeoutException) {
                    responseData.setTimeout(true);
                } else if (e instanceof InterruptedIOException && TextUtils.equals(e.getMessage(),
                        "timeout")) {
                    responseData.setTimeout(true);
                }
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (response != null) {
                    responseData.setResponseNull(false);
                    responseData.setCode(response.code());
                    responseData.setMessage(response.message());
                    responseData.setSuccess(response.isSuccessful());
                    String respBody = "";
                    try {
                        respBody = response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    responseData.setResponse(respBody);
                    responseData.setHeaders(response.headers());
                } else {
                    responseData.setResponseNull(true);
                }
                isSuccessMethod(responseData);
            }
        });
        return responseData;
    }

    protected void isSuccessMethod(ResponseData responseData) {
        if (!HttpTaskHandler.getInstance().contains(requestKey)) {
            return;
        }

        //判断请求是否在这个集合中
        if (!responseData.isResponseNull()) {//请求得到响应
            if (responseData.isSuccess()) {//成功的请求
                String respBody = responseData.getResponse();
                if (Constants.DEBUG) {
                    Log.d("HttpTask", "url=" + url + "\n result=" + JsonFormatUtils.formatJson(respBody));
                }
                Message message = Message.obtain();
                message.what = CALLBACK_SUCCESSFUL;
                message.obj = respBody;
                mHandler.sendMessage(message);
//                parseResponseBody(respBody, callback);
//        }

            } else {//请求失败
                int code = responseData.getCode();
                String msg = responseData.getMessage();
                if (Constants.DEBUG) {
                    Log.d("HttpTask", "url=" + url + "\n response failure code=" + code + " msg=" + msg);
                }
                if (code == 504) {
                    if (callback != null) {
//                        callback.onFailure(BaseHttpRequestCallback.ERROR_RESPONSE_TIMEOUT,
//                                "network error time out");
                        Message message = Message.obtain();
                        message.what = ERROR_RESPONSE_TIMEOUT;
                        mHandler.sendMessage(message);
                    }
                } else if (code == 500) {
                    Message message = Message.obtain();
                    message.what = CODE_REE;
                    mHandler.sendMessage(message);
                } else {
                    if (callback != null) {
//                        callback.onFailure(code, msg);
                        Message message = Message.obtain();
                        message.what = CALLBACK_FAILED;
                        Bundle bundle = new Bundle();
                        bundle.putInt("code", code);
                        bundle.putString("msg", msg);
                        message.obj = bundle;
                        mHandler.sendMessage(message);
                    }
                }
            }
        } else {//请求无响应
            if (responseData.isTimeout()) {
                if (callback != null) {
//                    callback.onFailure(BaseHttpRequestCallback.ERROR_RESPONSE_TIMEOUT,
//                            "network error time out");
                    Message message = Message.obtain();
                    message.what = ERROR_RESPONSE_TIMEOUT;
                    mHandler.sendMessage(message);
                }
            } else {
                if (Constants.DEBUG) {
                    Log.d("HttpTask", "url=" + url + "\n response empty");
                }
                if (callback != null) {
//                    callback.onFailure(BaseHttpRequestCallback.ERROR_RESPONSE_UNKNOWN, "http exception");
                    Message message = Message.obtain();
                    message.what = ERROR_RESPONSE_UNKNOWN;
                    mHandler.sendMessage(message);
                }
            }
        }

        if (callback != null) {
//            callback.onFinish();
            Message message = Message.obtain();
            message.what = RESPONSE_FINISH;
            mHandler.sendMessage(message);
        }
    }

//    @Override
//    protected void onPostExecute(ResponseData responseData) {
//        super.onPostExecute(responseData);
//
//        if (!HttpTaskHandler.getInstance().contains(requestKey)) {
//            return;
//        }
//
//        //判断请求是否在这个集合中
//        if (!responseData.isResponseNull()) {//请求得到响应
//            if (responseData.isSuccess()) {//成功的请求
//                String respBody = responseData.getResponse();
//                if (Constants.DEBUG) {
//                    Log.d("HttpTask","url=" + url +  "\n result=" + JsonFormatUtils.formatJson(respBody));
//                }
//                parseResponseBody(respBody, callback);
//            } else {//请求失败
//                int code = responseData.getCode();
//                String msg = responseData.getMessage();
//                if (Constants.DEBUG) {
//                    Log.d("HttpTask","url=" + url + "\n response failure code=" + code + " msg=" + msg);
//                }
//                if (code == 504) {
//                    if (callback != null) {
//                        callback.onFailure(BaseHttpRequestCallback.ERROR_RESPONSE_TIMEOUT,
//                                "network error time out");
//                    }
//                } else {
//                    if (callback != null) {
//                        callback.onFailure(code, msg);
//                    }
//                }
//            }
//        } else {//请求无响应
//            if (responseData.isTimeout()) {
//                if (callback != null) {
//                    callback.onFailure(BaseHttpRequestCallback.ERROR_RESPONSE_TIMEOUT,
//                            "network error time out");
//                }
//            } else {
//                if (Constants.DEBUG) {
//                    Log.d("HttpTask","url=" + url + "\n response empty");
//                }
//                if (callback != null) {
//                    callback.onFailure(BaseHttpRequestCallback.ERROR_RESPONSE_UNKNOWN, "http exception");
//                }
//            }
//        }
//
//        if (callback != null) {
//            callback.onFinish();
//        }
//    }

//    /**
//     * 解析响应数据
//     * @param result 请求的response 内容
//     * @param callback 请求回调
//     */
//    private void parseResponseBody(String result, BaseHttpRequestCallback callback) {
//
//        //回调为空，不向下执行
//        if(callback == null){
//            return;
//        }
//
//        if (StringUtils.isEmpty(result) || !new JsonValidator().validate(result)) {
//            callback.onFailure(BaseHttpRequestCallback.ERROR_RESPONSE_NULL, "result empty");
//            return;
//        }
////        ApiResponse response = null;
////        try {
////            Gson gson = new Gson();
////            Object obj = gson.fromJson(result, callback.getModelClazz());
////            if (obj instanceof ApiResponse) {
////                response = (ApiResponse) obj;
////            } else {
////                response = gson.fromJson(result, ApiResponse.class);
////            }
////        } catch (Exception e) {
////            Log.e("HttpTask",e.toString());
////        }
////        if (response != null) {
////            //默认超时
////            callback.onSuccess(response);
////            return;
////        }
//
//        ApiResponse response = new ApiResponse();
//        response.setResponse(result);
//        if (response != null) {
//            callback.onSuccess(response);
//            return;
//        }
//
//        //接口请求失败
//        callback.onFailure(BaseHttpRequestCallback.ERROR_RESPONSE_JSON_EXCEPTION, "json exception");
//        return;
//    }


    static class UIHandler<T> extends Handler {
        private WeakReference mWeakReference;

        public UIHandler(BaseHttpRequestCallback callback) {
            super(Looper.getMainLooper());
            mWeakReference = new WeakReference(callback);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CALLBACK_SUCCESSFUL: {
                    BaseHttpRequestCallback callback = (BaseHttpRequestCallback) mWeakReference.get();
                    String t = (String) msg.obj;
                    if (StringUtils.isEmpty(t)) {
                        callback.onFailure(BaseHttpRequestCallback.ERROR_RESPONSE_NULL, "result empty");
                    } else if (!new JsonValidator().validate(t)) {
                        callback.onFailure(BaseHttpRequestCallback.ERROR_RESPONSE_JSON_EXCEPTION, "network error time out");
                    } else {
                        ApiResponse response = new ApiResponse();
                        response.setResponse(t);
                        if (callback != null) {
                            callback.onSuccess(response);
                        }
                    }
                    break;
                }
                case CALLBACK_FAILED: {
                    BaseHttpRequestCallback callback = (BaseHttpRequestCallback) mWeakReference.get();
                    if (callback != null) {
                        Bundle bundle = msg.getData();
                        int code = bundle.getInt("code");
                        String msgs = bundle.getString("msg");
                        callback.onFailure(code, msgs);
                    }
                    break;
                }
                case ERROR_RESPONSE_TIMEOUT: {
                    BaseHttpRequestCallback callback = (BaseHttpRequestCallback) mWeakReference.get();
                    if (callback != null) {
                        callback.onFailure(BaseHttpRequestCallback.ERROR_RESPONSE_TIMEOUT, "网络超时！请检查网络");
                    }
                    break;
                }
                case CODE_REE: {
                    BaseHttpRequestCallback callback = (BaseHttpRequestCallback) mWeakReference.get();
                    if (callback != null) {

                        callback.onFailure(BaseHttpRequestCallback.ERROR_RESPONSE_TIMEOUT, "CODE_REE");
                    }
                    break;
                }
                case ERROR_RESPONSE_UNKNOWN: {
                    BaseHttpRequestCallback callback = (BaseHttpRequestCallback) mWeakReference.get();
                    if (callback != null) {
                        callback.onFailure(BaseHttpRequestCallback.ERROR_RESPONSE_UNKNOWN, "http exception");
                    }
                    break;
                }
                case RESPONSE_FINISH: {
                    BaseHttpRequestCallback callback = (BaseHttpRequestCallback) mWeakReference.get();
                    if (callback != null) {
                        callback.onFinish();
                    }
                    break;
                }
//                case RESPONSE_START: {
//                    BaseHttpRequestCallback callback = (BaseHttpRequestCallback) mWeakReference.get();
//                    if (callback != null) {
//                        callback.onStart();
//                    }
//                    break;
//                }
                default:
                    super.handleMessage(msg);
                    break;
            }
        }
    }

}
