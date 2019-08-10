package com.wanderingthinkter.tummy.api

import android.content.Context
import com.android.volley.Response.*
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class RestApi(val ctx: Context) {

    private val baseUrl = "http://tummy.us-east-2.elasticbeanstalk.com"

    private val requestQueue = Volley.newRequestQueue(ctx)

    fun request(method: Int, url: String, body: JSONObject, listenerFun: Listener<JSONObject>,
                errorListenerFun: ErrorListener) {
        val requestObj = JsonObjectRequest(method, "$baseUrl$url", body, listenerFun, errorListenerFun)
        requestQueue.add(requestObj)
    }

}