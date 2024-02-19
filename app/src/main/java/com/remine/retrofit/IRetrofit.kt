package com.remine.retrofit

import com.google.gson.JsonElement
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface IRetrofit {

//    @POST("a")
//    fun tokenRefresh(@Body refreshTokenArray : String): Call<AuthRes>
//
//
//    // 사장 회원가입
    @GET("/api/v1/declarations")
    fun getDeclarations(): Call<JsonElement>

    @Multipart
    @POST("/api/v1/declarations")
    fun postDeclarations(@Part file: MultipartBody.Part,@Part("content") content: RequestBody): Call<JsonElement>


}
