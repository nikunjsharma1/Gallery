package com.nikunj.galleryapp.ui

import androidx.lifecycle.*
import com.nikunj.galleryapp.data.Result
import com.nikunj.galleryapp.data.model.GalleryRespose
import com.nikunj.galleryapp.data.remote.MainDataSource
import com.nikunj.galleryapp.repo.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val mainDataSource: MainDataSource) : ViewModel() {

    private val _galleryList = MutableLiveData<List<GalleryRespose>>()

    lateinit var responseError:String

    val galleryList: LiveData<List<GalleryRespose>> = _galleryList
    fun getGallery(method: String, perPage: Int, page: Int, apiKey: String, format: String, nojsoncallback: Int, extras: String) {
       viewModelScope.launch {
           when(val result= mainDataSource.getGallery(method, perPage, page, apiKey, format, nojsoncallback, extras)){
               is Result.Success->{
                   _galleryList.postValue(listOf(result.data))
               }
               is Result.Error->{
                  responseError= result.message.toString()
               }
           }
       }
    }


}


