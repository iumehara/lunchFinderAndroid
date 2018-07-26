package io.umehara.lunchfinderandroid

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.umehara.lunchfinderandroid.di.DaggerApplicationComponent

class MainApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder().create(this)
    }
}