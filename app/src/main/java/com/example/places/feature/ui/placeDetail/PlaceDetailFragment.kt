package com.example.places.feature.ui.placeDetail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.places.R
import com.example.places.databinding.FragmentPlaceDetailBinding
import com.example.places.feature.base.BaseFragment
import com.example.places.feature.ui.placeMap.PlaceMapFragment
import com.example.places.feature.utils.parcelable
import com.example.places.model.PlaceDetailModelView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PlaceDetailFragment : BaseFragment<FragmentPlaceDetailBinding,PlaceDetailViewModel>(FragmentPlaceDetailBinding::inflate) {

    private val idPlaceBundle by lazy { arguments?.getString(KEY_ID_PLACE) }
    private var placeDetail : PlaceDetailModelView? = null

    override val classTypeOfVM: Class<PlaceDetailViewModel>
        get() = PlaceDetailViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        idPlaceBundle?.let { id ->
            myViewModel.executeUseCasePlaceDetail(id)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                myViewModel.state.collectLatest { state ->
                    when(state){
                       is UIPlaceDetailState.OnDetailIsLoaded -> setData(state)
                    }
                }
            }
        }

        configView()
    }

    private fun configView(){
        with(binding){
            btnGoToMap.setOnClickListener {
                val bundle = Bundle().apply {
                    putParcelable(PlaceMapFragment.KEY_PLACE_DETAIL_BUNDLE,placeDetail)
                }
                findNavController().navigate(R.id.placeMapFragment,bundle)
            }
        }
    }

    private fun setData(state: UIPlaceDetailState.OnDetailIsLoaded){
        placeDetail = state.detail
        with(binding){
            Glide
                .with(imgPlace.context)
                .load(state.detail.photo)
                .into(imgPlace)
            //.error(R.drawable)
            tvTitle.text = state.detail.name
            tvDetailContent.text = state.detail.description
            tvAddress.text = state.detail.address
        }
    }

    companion object {
        const val KEY_ID_PLACE = "key_idPlace.ui.placeDetail"
    }
}