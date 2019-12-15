package com.michael.domain

import io.reactivex.Single

interface Repository {
    fun getData(): Single<Pair<String, List<RowModel>>>
}