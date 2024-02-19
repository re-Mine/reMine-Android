package com.remine

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class App : Application() {

    companion object{
        lateinit var instance: App
        lateinit var context: Context

        lateinit var sharedPreferences: SharedPreferences

        var accessToken:String? = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5IiwiZXhwIjoxNzA4MzcxNjc4fQ.VJgUYTIJ7GT7cZf__cnIcHgIpdwz1Uzft5cHLdwSGyb9NKgTOr6htHWEoegz-Pltan9Rj6WL_ge2RRtJtoVt7w"
        var refreshToken:String? = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5IiwiZXhwIjoxNzA4OTI1MzA2fQ.bniLIibySkWc1XYem9Y5Mryn3zkVUMtWsYz51FC7wqXXB94fYjwTphB5idfimFRXbR4mIrppVHDAqxFZ9dHWVg"

        var userName:String? = "OO"
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

        val userName = userName
        sharedPreferences?.edit()?.putString("user_name", userName)?.apply()

        App.userName = sharedPreferences?.getString("user_name", "OO")
    }

}