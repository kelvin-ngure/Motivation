package com.happymeerkat.motivated.ui.views

import android.content.Context
import android.os.Build
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions

@GlideModule
class MyAppGlideModule : AppGlideModule() { // leave empty for now
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        builder.setDefaultRequestOptions(RequestOptions().format(getBitmapQuality()))
    }

    private fun getBitmapQuality(): DecodeFormat {
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            // return worse bitmap quality for low end devices
            DecodeFormat.PREFER_RGB_565
        } else {
            DecodeFormat.PREFER_ARGB_8888
        }
    }
}