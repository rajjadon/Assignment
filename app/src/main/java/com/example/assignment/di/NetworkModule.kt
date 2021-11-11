/*
 * Created by Raj Pratap Singh Jadon on 13/08/21, 5:36 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 12/08/21, 4:31 PM
 */

package com.android.wakeMate.di


import android.content.Context
import com.example.assignment.BuildConfig
import com.example.assignment.data.remote.ApiService
import com.squareup.moshi.*
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideLoggingInterceptor() =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Singleton
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        cache: Cache,
    ) = OkHttpClient().newBuilder().apply {

        callTimeout(40, TimeUnit.SECONDS)
        connectTimeout(40, TimeUnit.SECONDS)
        readTimeout(40, TimeUnit.SECONDS)
        writeTimeout(40, TimeUnit.SECONDS)
        addInterceptor(loggingInterceptor)
        cache(cache)
        retryOnConnectionFailure(true)
    }.build()

    @Singleton
    @Provides
    fun provideCache(@ApplicationContext application: Context): Cache {
        val cacheSize = 10L * 1024 * 1024 // 10 MB
        val httpCacheDirectory = File(application.cacheDir, "http-cache")
        return Cache(httpCacheDirectory, cacheSize)
    }

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(object : Any() {
            @ToJson
            fun toJson(writer: JsonWriter, o: Nothing?) {
                writer.nullValue()
                Timber.d(o.toString())
            }

            @FromJson
            fun fromJson(reader: JsonReader): Nothing? {
                reader.skipValue()
                return null
            }
        })
        .add(KotlinJsonAdapterFactory())
        .build()

    @Singleton
    @Provides
    fun provideConverterFactory(
        moshi: Moshi
    ): Converter.Factory = MoshiConverterFactory.create(moshi)


    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideAuthApiService(retrofit: Retrofit): ApiService =
        retrofit.create((ApiService::class.java))

}