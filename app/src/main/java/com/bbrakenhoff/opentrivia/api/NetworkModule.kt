package com.bbrakenhoff.opentrivia.api

import com.bbrakenhoff.opentrivia.KoinModule
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import okhttp3.MediaType.Companion.toMediaType

object NetworkModule : KoinModule {

    @OptIn(UnstableDefault::class)
    override fun start() = module {
        // OkHttpClient
        factory {
            OkHttpClient.Builder()
                .addInterceptor(get<HttpLoggingInterceptor>())
                .build()
        }

        // HttpLoggingInterceptor
        factory { HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC } }

        // Retrofit
        single {
            val contentType = "application/json".toMediaType()

            Retrofit.Builder()
                .baseUrl("https://opentdb.com/")
                .client(get())
                .addConverterFactory(Json.asConverterFactory(contentType))
                .addConverterFactory(EnumConverterFactory())
                .build()
        }

        // TriviaApi
        single {
            get<Retrofit>().create(OpenTriviaApi::class.java)
        }
    }
}
