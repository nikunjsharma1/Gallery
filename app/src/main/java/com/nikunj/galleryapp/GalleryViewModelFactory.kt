package com.nikunj.galleryapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nikunj.galleryapp.data.remote.MainDataSource
import com.nikunj.galleryapp.ui.MainViewModel
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class GalleryViewModelFactory @Inject constructor(
    private val mainDataSource: MainDataSource
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(mainDataSource) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}