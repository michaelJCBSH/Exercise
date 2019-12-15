package com.michael.data

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.michael.domain.RowModel
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import javax.inject.Inject


class RemoteDataSource @Inject constructor(okHttpClient: OkHttpClient) {
    private val gson =
        GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()

    private var service: Service = Retrofit.Builder()
        .baseUrl("https://dl.dropboxusercontent.com")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .build()
        .create(Service::class.java)

    fun getData(): Single<Pair<String, List<RowModel>>> {
        return service.getData().map { Pair(it.title, it.rows) }
    }
}

interface Service {
    @GET("/s/2iodh4vg0eortkl/facts.js")
    fun getData(): Single<Response>
}

data class Response(val title: String, val rows: List<RowModel>)