package dev.andysims.greenthumb.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.andysims.greenthumb.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

enum class NetworkStatus { LOADING, ERROR, DONE }

private const val BASE_URL = "https://trefle.io/api/v1/?token=${BuildConfig.TREFLE_API_KEY}"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface TrefleApiService {
    @GET("plants")
    suspend fun getPlants(): TrefleApiResponse
}

object TrefleApi {
    val service by lazy {
        retrofit.create(TrefleApiService::class.java)
    }
}