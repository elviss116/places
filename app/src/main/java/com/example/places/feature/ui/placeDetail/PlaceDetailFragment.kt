package com.example.places.feature.ui.placeDetail

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
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
import com.example.places.feature.utils.themeColor
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
                        is UIPlaceDetailState.OnVerifyFavorite -> setIconFavorite(state.isFavorite)
                        is UIPlaceDetailState.OnAddOrDeleteFavorite -> showToast(state.msg)
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
            btnAddFavorite.setOnClickListener {
                placeDetail?.let { pd ->
                    myViewModel.executeUseCaseAddFavorite(pd)
                }
            }
        }
    }

    private fun setIconFavorite(isfavorite: Boolean){
        if (isfavorite){
            binding.btnAddFavorite.setImageResource(R.drawable.ic_favorite)
        }else{
            binding.btnAddFavorite.setImageResource(R.drawable.ic_favorite_no)
        }
        val primaryColor = requireContext().themeColor(androidx.appcompat.R.attr.colorPrimary)
        DrawableCompat.setTint(binding.btnAddFavorite.drawable, primaryColor)
    }

    private fun setData(state: UIPlaceDetailState.OnDetailIsLoaded){
        placeDetail = state.detail
        myViewModel.executeUseCaseVerifyFavoriteUseCase(state.detail.id)
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