package com.remine.ui.declaration

import com.google.gson.annotations.SerializedName

data class Declaration(
    @SerializedName("declarationId")
    val declarationId: Int,
    @SerializedName("createdDate")
    val date: String,
    @SerializedName("content")
    val declaration: String,
    @SerializedName("audioFileURL")
    val voice: String
)