package com.michael.data

import com.michael.domain.Repository
import com.michael.domain.RowModel
import dagger.Provides
import io.reactivex.Single
import javax.inject.Inject

class RepositoryImpl @Inject constructor(val dataSource: RemoteDataSource) : Repository {
    override fun getData(): Single<Pair<String, List<RowModel>>> {
        return dataSource.getData()
    }
}