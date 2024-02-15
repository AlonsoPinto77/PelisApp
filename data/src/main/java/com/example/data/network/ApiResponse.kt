package com.example.data.network

import com.google.gson.annotations.SerializedName

data class ApiResponse<T>(
    @SerializedName("dates") val dates: ApiDatesResponse,
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: T,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
) {
}