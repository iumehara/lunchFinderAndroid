package io.umehara.lunchfinderandroid.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.umehara.lunchfinderandroid.MainActivity
import io.umehara.lunchfinderandroid.MainActivityModule

@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class))
    abstract fun bindMainActivity(): MainActivity
}