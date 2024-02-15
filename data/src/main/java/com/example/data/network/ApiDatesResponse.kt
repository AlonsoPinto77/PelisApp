package com.example.data.network

import com.google.gson.annotations.SerializedName

data class ApiDatesResponse(
    @SerializedName("maximum")
    val maximum: String,
    @SerializedName("minimum")
    val minimun: String
) {
}