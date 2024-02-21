package com.remine.retrofit

import com.google.gson.JsonElement
import com.remine.ui.declaration.DeclarationResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface IRetrofit {

    @GET(API.DECLARATION)
    fun getDeclarations(): Call<DeclarationResponse>

    @Multipart
    @POST(API.DECLARATION)
    fun postDeclarations(@Part file: MultipartBody.Part,@Part("content") content: RequestBody): Call<JsonElement>

}
