package com.example.places.feature.utils

import android.content.Context
import android.content.Intent
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable
import android.text.InputFilter
import android.util.TypedValue
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.places.R

/** FUNCION DE EXTENSION PARA OBTENER PARCELABLES SEGUN LOS CAMBIOS PARA API 33 **/
inline fun <reified T : Parcelable> Intent.parcelable(key: String): T? = when {
    SDK_INT >= 33 -> getParcelableExtra(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelableExtra(key) as? T
}

inline fun <reified T : Parcelable> Bundle.parcelable(key: String): T? = when {
    SDK_INT >= 33 -> getParcelable(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelable(key) as? T
}

/** FUNCION DE EXTENSION PARA OBTENER EL COLOR PRIMARIO DEL THEME **/
@ColorInt
fun Context.themeColor(@AttrRes attrRes: Int): Int = TypedValue()
    .apply { theme.resolveAttribute (attrRes, this, true) }
    .data

/** FUNCION DE EXTENSION PARA ASIGNAR UN MAXLENGTH A UN TEXTVIEW **/
fun TextView.setMaxLength(maxLength: Int){
    filters = arrayOf<InputFilter>(InputFilter.LengthFilter(maxLength))
}

/** FUNCION CARGAR IMAGENES USANDO GLIDE **/
fun ImageView.loadImageUrl(url: String) {
    val placeHolder = R.drawable.placeholder
    if (url.isNotEmpty()) {
        Glide.with(this.context).load(url).apply(RequestOptions.placeholderOf(placeHolder)
            .error(placeHolder).dontTransform()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
        ).into(this)
    }else{
        setImageResource(placeHolder)
    }
}