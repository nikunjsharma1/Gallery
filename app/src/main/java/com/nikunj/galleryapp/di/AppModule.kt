package com.nikunj.galleryapp.di

import android.app.Application
import android.content.Context
import com.nikunj.galleryapp.GalleryApplication
import com.nikunj.galleryapp.data.remote.ApiService
import com.nikunj.galleryapp.data.remote.MainDataSource
import com.nikunj.galleryapp.repo.MainRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class AppModule(galleryApplication: GalleryApplication) {
    @Singleton
    @Provides
    fun provideApplicationContext(application: Application): Context {
        return application

    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.flickr.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
    @Provides
    @Singleton
    fun provideMainDataSource(MainRepository: MainRepository): MainDataSource {
        return MainRepository
    }

}