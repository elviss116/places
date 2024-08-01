package com.example.places.feature.ui.place

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.places.R
import com.example.places.databinding.FragmentPlaceBinding
import com.example.places.feature.adapter.PlacesAdapter
import com.example.places.feature.base.BaseFragment
import com.example.places.model.PlacesModelView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PlaceFragment : BaseFragment<FragmentPlaceBinding,PlaceViewModel>(FragmentPlaceBinding::inflate) {

    private val placeAdapter = PlacesAdapter(listOf(),::onClickPlace)

    override val classTypeOfVM: Class<PlaceViewModel>
        get() = PlaceViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configView()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                myViewModel.state.collectLatest { state ->
                    when(state){
                        is UIPlaceState.OnPlacesIsLoaded -> placeAdapter.addItems(state.list)
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

    private fun onClickPlace(place: PlacesModelView){

    }
}