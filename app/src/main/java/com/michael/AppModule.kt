package com.michael

import androidx.lifecycle.ViewModelProvider
import com.michael.data.RepositoryImpl
import com.michael.domain.Repository
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    abstract fun provideRepository(o: RepositoryImpl) : Repository

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: MyViewModelFactory): ViewModelProvider.Factory
}