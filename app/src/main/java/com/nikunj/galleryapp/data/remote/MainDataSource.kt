package com.nikunj.galleryapp.data.remote

import androidx.lifecycle.MutableLiveData
import com.nikunj.galleryapp.data.Result
import com.nikunj.galleryapp.data.model.GalleryRespose
import retrofit2.http.Query

interface MainDataSource {
    suspend fun getGallery(
        method: String,
        perPage: Int,
        page: Int,
        apiKey: String,
        format: String,
        nojsoncallback: Int,
        extras: String
    ): Result<GalleryRespose>
}
