package com.michael

import androidx.lifecycle.ViewModel
import com.michael.presentation.ListFragment
import com.michael.presentation.ListViewModel
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(
        includes = [
            ListFragmentModule::class
        ]
)
abstract class ContributeFragmentModule


@Module
abstract class ListFragmentModule {

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeFragment(): ListFragment

    @Module
    abstract class ViewModelModule {
        @Binds
        @IntoMap
        @ViewModelKey(ListViewModel::class)
        abstract fun bindViewModel(viewModel: ListViewModel): ViewModel
    }
}
