package com.example.comik.ui.listcomic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.comik.base.RxViewModel
import com.example.comik.data.model.Comic
import com.example.comik.data.repository.ComicRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class ListComicViewModel(
    private val comicRepository: ComicRepository
) : RxViewModel() {

    private val _comic = MutableLiveData<List<Comic>>()
    val comic: LiveData<List<Comic>> get() = _comic

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _description = MutableLiveData<String>()
    val description: LiveData<String>
        get() = _description

    private val _empty = MutableLiveData<Boolean>()
    val empty: LiveData<Boolean>
        get() = _empty

    fun getComicsByType(type: String, id: Int, description: String) {
        _isLoading.value = true
        _description.value = description
        _empty.value = description != ""
        comicRepository.getComicsByType(type, id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _isLoading.value = false
                _comic.value = it
            }, {
                _isLoading.value = false
                _error.value = it.message.toString()
            })
            .addTo(disposables)
    }
}
