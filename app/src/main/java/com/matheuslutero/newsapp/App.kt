package com.matheuslutero.newsapp

import android.app.Application
import coil.Coil
import coil.ImageLoader
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    @Inject
    fun setImageLoader(imageLoader: ImageLoader) {
        Coil.setImageLoader(imageLoader)
    }
}
