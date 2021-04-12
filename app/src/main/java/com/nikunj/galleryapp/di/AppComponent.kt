package com.nikunj.galleryapp.di

import android.app.Application
import com.nikunj.galleryapp.GalleryApplication
import com.nikunj.galleryapp.ui.MainActivity
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidInjectionModule::class,AppModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}
