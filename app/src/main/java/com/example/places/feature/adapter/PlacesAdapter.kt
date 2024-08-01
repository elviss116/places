package com.example.places.feature.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.places.R
import com.example.places.databinding.ItemPlaceBinding
import com.example.places.feature.base.diffList
import com.example.places.model.PlacesModelView

class PlacesAdapter(
    private var list: List<PlacesModelView>,
    private val onClick: (PlacesModelView) -> Unit
) : RecyclerView.Adapter<PlacesAdapter.PlacesHolder>(){

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

    fun addItems(newList: List<PlacesModelView>){
        val oldList = list
        list = newList
        diffList(oldList,newList, sameItem = {a,b -> a.id == b.id}).dispatchUpdatesTo(this)
    }

    inner class PlacesHolder(
        private val binding: ItemPlaceBinding,
        private val onClick: (PlacesModelView) -> Unit
    ) : RecyclerView.ViewHolder(binding.root){

        fun setItems(item: PlacesModelView){
            with(binding){
                Glide
                    .with(imgPlace.context)
                    .load(item.photo)
                    .into(imgPlace)
                //.error(R.drawable)
                tvTitle.text = item.title
                tvDescription.text = item.description

                root.setOnClickListener {
                    onClick.invoke(item)
                }
            }
        }
    }
}