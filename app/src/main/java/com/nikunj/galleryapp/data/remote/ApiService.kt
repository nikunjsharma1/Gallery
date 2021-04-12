package com.nikunj.galleryapp.data.remote
import com.nikunj.galleryapp.data.model.GalleryRespose
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("services/rest/")
    suspend fun getGallery(
        @Query("method") method: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String,
        @Query("format") format: String,
        @Query("nojsoncallback") nojsoncallback: Int,
        @Query("extras") extras: String
    ): GalleryRespose
}
//https://api.flickr.com/services/rest/?method=flickr.photos.getRecent&per_page=20&page=1&api_key=6f102c62f41998d151e5a1b48713cf13&format=json&nojsoncallback=1&extras=url_s