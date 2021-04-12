package com.nikunj.galleryapp

import android.app.Application
import com.nikunj.galleryapp.di.AppComponent
import com.nikunj.galleryapp.di.AppModule
import com.nikunj.galleryapp.di.DaggerAppComponent

class GalleryApplication : Application(){
    companion object {
        lateinit var instance: GalleryApplication
    }

    lateinit var libraryComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        libraryComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }
}