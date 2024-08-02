package com.example.places.feature.dialog.noInternet

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.example.places.databinding.DialogNoInternetBinding

class NoInternetDialog(
    private val onAccept: (()-> Unit)? = null
) : DialogFragment() {

    private var _binding : DialogNoInternetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (dialog != null && dialog?.window != null) {
            dialog?.window?.setBackgroundDrawable(InsetDrawable(ColorDrawable(Color.TRANSPARENT),40,10,40,10));
            dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE);
        }
        _binding = DialogNoInternetBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnOk.setOnClickListener {
            onAccept?.invoke()
            dismiss()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(
            onAccept: (()-> Unit)? = null,
        ) = NoInternetDialog(onAccept).apply {}
        const val TAG = "TAG_DFI.features.dialog.noInternet"
    }

    override fun onStart() {
        super.onStart()
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog?.window?.setLayout(width, height)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}