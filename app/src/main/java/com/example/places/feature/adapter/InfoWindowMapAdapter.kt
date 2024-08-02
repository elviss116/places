package com.example.places.feature.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.Nullable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.places.databinding.LayoutInfoWindowBinding
import com.example.places.model.PlaceDetailModelView
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.google.android.material.shape.*


class InfoWindowMapAdapter(
    private var pd: PlaceDetailModelView, context: Context
) : GoogleMap.InfoWindowAdapter{

    private val images: HashMap<Marker, Bitmap> = HashMap()
    private val targets: HashMap<Marker, CustomTarget<Bitmap>> = HashMap()

    private val binding: LayoutInfoWindowBinding = LayoutInfoWindowBinding.inflate(
        LayoutInflater.from(context),null,false
    )


    override fun getInfoContents(p0: Marker): View? {
        return null
    }

    override fun getInfoWindow(marker: Marker): View? {
        with(binding){
            cardContainer.shapeAppearanceModel = cardContainer.shapeAppearanceModel.toBuilder()
                .setBottomEdge(TriangleEdgeTreatment(40f,false))
                .build()


            val image = images[marker]
            if (image == null) {
                Glide.with(imgPlace.context)
                    .asBitmap()
                    .load(pd.photo)
                    .dontAnimate()
                    .into(getTarget(marker))
            } else {
                imgPlace.setImageBitmap(image)
            }

            tvName.text = pd.name
            tvAddress.text = pd.address
        }



        return binding.root
    }

    private fun View.setShapeBackground() {
        val cornerSize = 8F
        background = MaterialShapeDrawable(
            ShapeAppearanceModel.builder()
                .setAllCornerSizes(cornerSize)
                .setTopLeftCorner(CutCornerTreatment())
                .setTopEdge(TriangleEdgeTreatment(cornerSize, true))
                .setBottomEdge(TriangleEdgeTreatment(cornerSize, true))
                .setLeftEdge(TriangleEdgeTreatment(cornerSize, false))
                .setRightEdge(TriangleEdgeTreatment(cornerSize, false))
                .build()
        )
    }



    inner class InfoTarget(private var marker: Marker) : CustomTarget<Bitmap>() {
        override fun onLoadCleared(@Nullable placeholder: Drawable?) {
            images.remove(marker)
        }

        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
            images.put(marker, resource)
            marker.showInfoWindow()
        }
    }

    private fun getTarget(marker: Marker): CustomTarget<Bitmap> {
        var target = targets[marker]
        if (target == null) {
            target = InfoTarget(marker)
            targets[marker] = target
        }
        return target
    }
}