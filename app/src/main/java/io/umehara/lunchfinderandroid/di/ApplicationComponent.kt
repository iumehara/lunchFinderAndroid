package io.umehara.lunchfinderandroid.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import io.umehara.lunchfinderandroid.MainApplication

@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        ActivityBindingModule::class))
interface ApplicationComponent: AndroidInjector<MainApplication> {
    @Component.Builder
    abstract class Builder: AndroidInjector.Builder<MainApplication>()
}