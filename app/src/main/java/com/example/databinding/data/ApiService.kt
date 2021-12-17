package com.example.databinding.data

import com.example.databinding.model.login.AgentLoginResponse
import com.example.databinding.model.profile.AgentProfileResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.util.concurrent.TimeUnit


interface ApiService {

    /* API' s */

    /* Login Api */
    @FormUrlEncoded
    @POST("agent/login")
    suspend fun login(
        @Field("phone") phone: String,
        @Field("password") password: String
    ): Response<AgentLoginResponse>

    /* Profile Api */
    @POST("agent/profile")
    suspend fun profile(): Response<AgentProfileResponse>


    /* retrofit builder */
    companion object {
        fun create(): ApiService {
            val retrofit = retrofit2.Retrofit.Builder()
                .client(initializeClient())
                .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                .baseUrl("http://ncomfort.iroidtechnologies.in/api/")
                .build()
            return retrofit.create(ApiService::class.java)
        }

        private fun initializeClient(): OkHttpClient {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.HEADERS
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            System.setProperty("http.keepAlive", "false")
            val builder = OkHttpClient.Builder()
            builder.addInterceptor(interceptor)
                .addInterceptor(SupportInterceptor())
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
            return builder.build()
        }
    }
}