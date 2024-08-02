package com.example.places.feature.ui.favorite

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.places.R
import com.example.places.databinding.FragmentFavoriteBinding
import com.example.places.feature.adapter.PlacesFavoriteAdapter
import com.example.places.feature.base.BaseFragment
import com.example.places.feature.ui.placeMap.PlaceMapFragment.Companion.KEY_PLACE_DETAIL_BUNDLE
import com.example.places.model.PlaceDetailModelView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding,PlaceFavoriteViewModel>(FragmentFavoriteBinding::inflate) {

    override val classTypeOfVM: Class<PlaceFavoriteViewModel>
        get() = PlaceFavoriteViewModel::class.java

    private val placeAdapter = PlacesFavoriteAdapter(listOf(),::onPlaceIsClicked)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configView()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                myViewModel.state.collectLatest { state ->
                    when(state){
                        is UIPlaceFavoriteState.OnPlaceFavoriteIsLoaded -> placeAdapter.addItems(state.list)
                    }
                }
            }
        }
    }

    private fun configView(){
        with(binding){
            rvPlaces.apply {
                layoutManager = GridLayoutManager(rvPlaces.context,2)
                adapter = placeAdapter
            }
        }
    }

    private fun onPlaceIsClicked(pd: PlaceDetailModelView){
        val bundle = Bundle().apply {
            putParcelable(KEY_PLACE_DETAIL_BUNDLE,pd)
        }
        findNavController().navigate(R.id.placeDetailFragment,bundle)
    }
}