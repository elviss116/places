package com.example.places.feature.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.places.R
import com.example.places.databinding.ItemPlaceBinding
import com.example.places.feature.base.diffList
import com.example.places.feature.utils.loadImageUrl
import com.example.places.feature.utils.setMaxLength
import com.example.places.model.PlaceDetailModelView
import com.example.places.model.PlacesModelView

class PlacesFavoriteAdapter(
    private var list: List<PlaceDetailModelView>,
    private val onClick: (PlaceDetailModelView) -> Unit
) : RecyclerView.Adapter<PlacesFavoriteAdapter.PlacesHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlacesHolder {
        val binding = ItemPlaceBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        )
        return PlacesHolder(binding,onClick)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: PlacesHolder, position: Int) {
        val place = list[position]
        holder.setItems(place)
    }

    fun addItems(newList: List<PlaceDetailModelView>){
        val oldList = list
        list = newList
        diffList(oldList,newList, sameItem = {a,b -> a.id == b.id}).dispatchUpdatesTo(this)
    }

    inner class PlacesHolder(
        private val binding: ItemPlaceBinding,
        private val onClick: (PlaceDetailModelView) -> Unit
    ) : RecyclerView.ViewHolder(binding.root){

        fun setItems(item: PlaceDetailModelView){
            with(binding){
                imgPlace.loadImageUrl(item.photo)
                tvTitle.text = item.name
                tvDescription.setMaxLength(70)
                tvDescription.text = item.description

                root.setOnClickListener {
                    onClick.invoke(item)
                }
            }
        }
    }
}