package com.remine.retrofit

object API {
    const val BASE_URL : String = "https://www.remine.site:8080/"

    const val AUTH_SIGN_UP : String = "auth/shop-owner/sign-up"

}

enum class RESPONSE_STATE {
    OKAY,
    FAIL
}