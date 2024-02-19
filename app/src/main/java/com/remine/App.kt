package com.remine

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class App : Application() {

    companion object{
        lateinit var instance: App
        lateinit var context: Context

        lateinit var sharedPreferences: SharedPreferences

        var accessToken:String? = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5IiwiZXhwIjoxNzA4MzM4NTA2fQ.582eUuCOU_-BuqDsxxwsPOYI3EO_VPEPzA8CaUlyoVCeuhxSbJkRAOiB7OfkM1YwInPaNtMEDWnCSDeT_Dt4Aw"
        var refreshToken:String? = "yJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5IiwiZXhwIjoxNzA4OTI1MzA2fQ.bniLIibySkWc1XYem9Y5Mryn3zkVUMtWsYz51FC7wqXXB94fYjwTphB5idfimFRXbR4mIrppVHDAqxFZ9dHWVg"
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        context = applicationContext

        sharedPreferences =
            applicationContext.getSharedPreferences("com.remine", MODE_PRIVATE)


        val accessToken = accessToken
        sharedPreferences?.edit()?.putString("access_token", accessToken)?.apply()

        App.accessToken = sharedPreferences?.getString("access_token", null)

        val refreshToken = refreshToken
        sharedPreferences?.edit()?.putString("refresh_token", refreshToken)?.apply()

        App.refreshToken = sharedPreferences?.getString("refresh_token", null)
    }

}