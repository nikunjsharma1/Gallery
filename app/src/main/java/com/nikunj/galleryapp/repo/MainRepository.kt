package com.nikunj.galleryapp.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nikunj.galleryapp.data.Result
import com.nikunj.galleryapp.data.model.GalleryRespose
import com.nikunj.galleryapp.data.remote.ApiService
import com.nikunj.galleryapp.data.remote.MainDataSource
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MainRepository @Inject constructor(private val apiService: ApiService):MainDataSource {
    override suspend fun getGallery(
        method: String,
        perPage: Int,
        page: Int,
        apiKey: String,
        format: String,
        nojsoncallback: Int,
        extras: String
    ):Result<GalleryRespose> {
        return try {
            val galleryRespose=apiService.getGallery(  method, perPage, page, apiKey, format, nojsoncallback, extras)
            Result.Success(galleryRespose)
        }catch (e:Exception){
            Result.Error(e.localizedMessage)
        }
    }

}



