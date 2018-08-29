package io.umehara.lunchfinderandroid.di

import android.content.Context
import dagger.Binds
import dagger.Module
import io.umehara.lunchfinderandroid.MainApplication

@Module
abstract class ApplicationModule {
    @Binds
    abstract fun bindContext(application: MainApplication): Context
}