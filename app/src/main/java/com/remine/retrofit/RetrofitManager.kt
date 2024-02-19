package com.remine.retrofit

import android.util.Log
import com.google.gson.JsonElement
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitManager {
    companion object {
        val instance = RetrofitManager()
    }

    // http 콜 만들기
    // 레트로핏 인터페이스 가져오기
    private val iRetrofit: IRetrofit? =
        RetrofitClient.getClient(API.BASE_URL)?.create(IRetrofit::class.java)
    private val Retrofit: IRetrofit? =
        RetrofitClient.getClient2("")?.create(IRetrofit::class.java)


    // 상황별 추천경험 조회 api
    fun getDeclarations(
        completion: (RESPONSE_STATE, JsonElement?) -> Unit
    ) {
        val call =
            iRetrofit?.getDeclarations()
                ?: return

        call.enqueue(object : Callback<JsonElement> {
            // 응답 성공
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(
                    "retrofit",
                    "RetrofitManager - onResponse() called / response : ${response.code()}"
                )

                when (response.code()) {
                    200 -> {
                        completion(RESPONSE_STATE.OKAY, response.body())
                    }
                    else -> {
                        completion(RESPONSE_STATE.OKAY, null)

                    }
                }

            }

            // 응답 실패
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d("retrofit", "RetrofitManager - onFailure() called / t: $t")
                completion(RESPONSE_STATE.FAIL, null)
            }

        })
    }

    fun postDeclarations(
        filedata: MultipartBody.Part,
        content: RequestBody,
        completion: (RESPONSE_STATE) -> Unit
    ) {
        val call =
            iRetrofit?.postDeclarations(filedata, content)
                ?: return

        call.enqueue(object : Callback<JsonElement> {
            // 응답 성공
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(
                    "retrofit",
                    "RetrofitManager - postDeclarations - onResponse() called / response : ${response.code()}"
                )

                when (response.code()) {
                    200 -> {
                        completion(RESPONSE_STATE.OKAY)
                    }
                    else -> {
                        completion(RESPONSE_STATE.OKAY)

                    }
                }

            }

            // 응답 실패
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d("retrofit", "RetrofitManager - postDeclarations - onFailure() called / t: $t")
                completion(RESPONSE_STATE.FAIL)
            }

        })
    }
}