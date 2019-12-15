package com.michael

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ContributeFragmentModule::class,
        AppModule::class,
        AppModule2::class
    ]
)
interface MyComponent : AndroidInjector<MyApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MyApplication>()
}