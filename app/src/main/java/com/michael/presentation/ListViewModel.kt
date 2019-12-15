package com.michael.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.michael.domain.Repository
import com.michael.domain.RowModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListViewModel @Inject constructor(private val repo: Repository) : ViewModel() {
    private val disposables = CompositeDisposable()
    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    private val title = MutableLiveData<String>()
    fun getTitleLiveData() : LiveData<String> = title
    private  val rows = MutableLiveData<List<RowModel>>()
    fun getRowsLiveData() : LiveData<List<RowModel>> = rows

    fun loadData() {
        repo.getData().observeOn(Schedulers.io())
            .subscribe(Consumer {
                title.postValue(it.first)
                rows.postValue(it.second)
            })
            .addTo(disposables)
    }
}
