package com.remine.retrofit

object API {
    const val BASE_URL : String = "https://www.remine.site:8080/"

    const val DECLARATION : String = "/api/v1/declarations"

}

enum class RESPONSE_STATE {
    OKAY,
    FAIL
}