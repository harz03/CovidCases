package com.harshit.covidtracker

import okhttp3.OkHttpClient
import okhttp3.Request

object Client {
    private val client = OkHttpClient()

    val request = Request.Builder().url("https://api.covid19india.org/data.json").build()
    val  callResponse = client.newCall(request)
}