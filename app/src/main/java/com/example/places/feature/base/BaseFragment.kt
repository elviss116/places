package com.example.places.feature.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.example.places.feature.dialog.LoadingDialog
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T
abstract class BaseFragment<VB: ViewBinding, ViewModelType: BaseViewModel>(private val inflate: Inflate<VB>) : Fragment(){

    private var _binding: VB? = null
    protected val binding get() = _binding!!
    private var myDialog: LoadingDialog? = null

    abstract val classTypeOfVM: Class<ViewModelType>
    lateinit var myViewModel: ViewModelType

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        myViewModel = ViewModelProvider(this)[classTypeOfVM]
        if (myViewModel == null) {
            throw Exception("View Model must not be null.")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myDialog = LoadingDialog()
        observerBaseState()
    }

    private fun observerBaseState(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                myViewModel.baseState.collectLatest { state ->
                    when(state){
                        is UIBaseState.OnLoading -> showLoading(state.show)
                        is UIBaseState.OnTimeExpired -> Unit//showTimeExpiredDialog(state.show)
                    }
                }
            }
        }
    }

    private fun showLoading(show: Boolean){
        if (show) {
            myDialog?.let { dialog ->
                if (!dialog.isAdded) {
                    dialog.show(
                        requireActivity().supportFragmentManager,
                        LoadingDialog.TAG
                    )//childFragmentManager
                // requireActivity().supportFragmentManager.executePendingTransactions()
                } else {

                }
            }

        } else {
            myDialog?.dismiss()
            //requireActivity().supportFragmentManager.executePendingTransactions()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}