package dev.andysims.greenthumb.network

import com.squareup.moshi.Json

data class Plant (
        val id: Int,
        @Json(name = "common_name") val commonName: String,
        @Json(name = "scientific_name") val scientificName: String,
        @Json(name = "image_url") val imageUrl: String
        )