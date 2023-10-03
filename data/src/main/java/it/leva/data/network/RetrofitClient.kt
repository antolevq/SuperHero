package it.leva.data.network

import it.leva.data.BuildConfig
import it.leva.data.network.service.HeroService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigInteger
import java.security.MessageDigest

object RetrofitClient {
    private fun instance(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(authInterceptor)
            .build()
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val authInterceptor = { chain: Interceptor.Chain ->
        val timestamp = System.currentTimeMillis()
        val request = chain.request()
        val url = request.url
            .newBuilder()
            .addQueryParameter("ts", timestamp.toString())
            .addQueryParameter("apikey", BuildConfig.PUBLIC_KEY)
            .addQueryParameter(
                "hash",
                "$timestamp${BuildConfig.PRIVATE_KEY}${BuildConfig.PUBLIC_KEY}".getMd5()
            )
            .build()
        val updated = request.newBuilder()
            .url(url)
            .build()

        chain.proceed(updated)

    }

    private fun String.getMd5(): String =
        BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
            .toString(16).padStart(32, '0')

    val heroService: HeroService = instance().create(HeroService::class.java)
}

