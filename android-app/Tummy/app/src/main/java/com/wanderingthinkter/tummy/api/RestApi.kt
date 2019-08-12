package com.wanderingthinkter.tummy.api

import android.content.Context
import com.android.volley.NetworkResponse
import com.android.volley.Response
import com.android.volley.Response.*
import com.android.volley.toolbox.HttpHeaderParser
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject
import java.nio.charset.Charset

class MetaRequest(
    method: Int,
    url: String?,
    jsonRequest: JSONObject?,
    listener: Listener<JSONObject>?,
    errorListener: ErrorListener?
) : JsonObjectRequest(method, url, jsonRequest, listener, errorListener) {

    override fun parseNetworkResponse(response: NetworkResponse?): Response<JSONObject> {
        try {
            val json = String(response?.data?: ByteArray(0),
                Charset.forName(HttpHeaderParser.parseCharset(response?.headers)))
            var result = JSONObject()
            if(json != null && json.isNotBlank()) {
                result = JSONObject(json)
            }
            result?.put("headers", response?.headers)
            return success(result, HttpHeaderParser.parseCacheHeaders(response))
        } catch(e: Exception) {
            return error(e)
        }
    }

}


class RestApi(val ctx: Context) {

    private val baseUrl = "http://tummy.us-east-2.elasticbeanstalk.com"

    private val requestQueue = Volley.newRequestQueue(ctx)

    fun request(method: Int, url: String, body: JSONObject, listenerFun: Listener<JSONObject>,
                errorListenerFun: ErrorListener) {
        val requestObj = MetaRequest(method, "$baseUrl$url", body, listenerFun, errorListenerFun)
        requestQueue.add(requestObj)
    }

    fun requestArray(method: Int, url: String, body: JSONArray, listenerFun: Listener<JSONArray>,
                     errorListenerFun: ErrorListener) {
        val requestObj = JsonArrayRequest(method, "$baseUrl$url", body, listenerFun, errorListenerFun)
        requestQueue.add(requestObj)
    }

}