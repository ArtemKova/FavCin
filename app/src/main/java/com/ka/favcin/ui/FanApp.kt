package com.ka.favcin.ui

import android.app.Application
import com.ka.favcin.newarch.di.DaggerApplicationComponent


class FanApp : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}