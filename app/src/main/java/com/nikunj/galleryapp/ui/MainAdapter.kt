package com.nikunj.galleryapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.nikunj.galleryapp.R
import com.nikunj.galleryapp.data.model.Photo
import com.nikunj.galleryapp.databinding.GalleryItemBinding

class MainAdapter(private var photo: List<Photo>) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    fun setDataList(data: List<Photo>?) {
        if (data != null) {
            this.photo = data
        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: GalleryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Photo) {
            binding.galleryItem = data
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.gallery_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(photo[position])
    }

    override fun getItemCount(): Int = if (photo.isNullOrEmpty()) 0 else photo.size
}



