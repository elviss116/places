package com.example.places.feature.ui.placeMap

import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.places.R
import com.example.places.databinding.FragmentPlaceMapBinding
import com.example.places.feature.adapter.InfoWindowMapAdapter
import com.example.places.feature.utils.parcelable
import com.example.places.model.PlaceDetailModelView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class PlaceMapFragment : Fragment(), OnMapReadyCallback {

    private var _binding : FragmentPlaceMapBinding? = null
    private val binding get() = _binding!!

    private val placeBundle by lazy { arguments?.parcelable<PlaceDetailModelView>(KEY_PLACE_DETAIL_BUNDLE) }
    private lateinit var map: GoogleMap
    private var infoAdapter : InfoWindowMapAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPlaceMapBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configMap()
    }

    private fun configMap(){
        val mapFragment: SupportMapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun createMarker(pd: PlaceDetailModelView){
        infoAdapter = InfoWindowMapAdapter(pd,requireContext())
        map.setInfoWindowAdapter(infoAdapter)
        val location = LatLng(pd.lat.toDouble(),pd.lng.toDouble())
        val marker = MarkerOptions()
            .position(location)
            .title("Lugar")
            .icon(bitMapFromVector(R.drawable.ic_marker))
        map.addMarker(marker)

        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(location,18f),
            2000,null
        )
    }

    private fun bitMapFromVector(vectorResID:Int): BitmapDescriptor {
        val vectorDrawable= ContextCompat.getDrawable(requireContext(),vectorResID)
        vectorDrawable!!.setBounds(0,0,vectorDrawable.intrinsicWidth,vectorDrawable.intrinsicHeight)
        val bitmap= Bitmap.createBitmap(vectorDrawable.intrinsicWidth,vectorDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888)
        val canvas= Canvas(bitmap)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        placeBundle?.let { pd ->
            createMarker(pd)
        }
    }
    companion object {
        private const val DEFAULT_ZOOM = 15
        const val KEY_PLACE_DETAIL_BUNDLE = "key_place_detail.ui.placeMap"
    }
}