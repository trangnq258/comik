package com.example.comik.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.comik.base.RxViewModel
import com.example.comik.data.model.Event
import com.example.comik.data.repository.EventRepository
import com.example.comik.data.model.Comic
import com.example.comik.data.model.Creator
import com.example.comik.data.repository.ComicRepository
import com.example.comik.data.repository.CreatorRepository
import com.example.comik.utils.APIConfig.FORMAT_COMIC
import com.example.comik.utils.APIConfig.IMAGE_NOT_AVAILABLE
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*

const val COUNT_EVENT = 6
const val COUNT_CREATOR = 7
const val COUNT_BANNER = 5

class HomeViewModel(
    private val eventRepository: EventRepository,
    private val creatorRepository: CreatorRepository,
    private val comicRepository: ComicRepository
) : RxViewModel() {

    private val _event = MutableLiveData<List<Event>>()
    val event: LiveData<List<Event>> get() = _event

    private val _creator = MutableLiveData<List<Creator>>()
    val creator: LiveData<List<Creator>> get() = _creator

    private val _comic = MutableLiveData<List<Comic>>()
    val comic: LiveData<List<Comic>> get() = _comic

    init {
        getEvents()
        getCreators()
        getBanners()
    }

    private fun getEvents() {
        eventRepository.getEvents()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _event.value = it.take(COUNT_EVENT)
            }, {
                _error.value = it.message.toString()
            })
            .addTo(disposables)
    }

    private fun getCreators() {
        creatorRepository.getCreators()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _creator.value = it.take(COUNT_CREATOR)
            }, {
                _error.value = it.message.toString()
            })
            .addTo(disposables)
    }

    private fun getBanners() {
        val calendar = Calendar.getInstance()
        comicRepository.getComicByYear(FORMAT_COMIC, calendar.get(Calendar.YEAR))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                it.filter { comic ->
                    comic.thumbnail?.path != IMAGE_NOT_AVAILABLE
                }
            }
            .subscribe({
                val randomNumber = (0..it.size - COUNT_BANNER).random()
                _comic.value = it.subList(randomNumber, randomNumber + COUNT_BANNER)
            }, {
                _error.value = it.message.toString()
            })
            .addTo(disposables)
    }
}
