package com.example.comik.ui.detail

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.comik.base.RxViewModel
import com.example.comik.data.model.*
import com.example.comik.data.repository.*
import com.example.comik.utils.BUNDLE
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import java.text.SimpleDateFormat

const val PRICE = "Price: "
const val INPUT_FORMAT_DATE = "yyyy-MM-dd'T'HH:mm:ss"
const val OUTPUT_FORMAT_DATE = "dd-MM-yyyy"
const val SUB_INDEX = 19
const val TYPE_PRICE = "$"

class DetailViewModel(
    private val comicRepository: ComicRepository,
    private val characterRepository: CharacterRepository,
    private val storyRepository: StoryRepository,
    private val creatorRepository: CreatorRepository,
    private val eventRepository: EventRepository
) : RxViewModel() {

    private val _comic = MutableLiveData<Comic>()
    val comic: LiveData<Comic>
        get() = _comic

    private val _isEmpty = MutableLiveData<Boolean>()
    val isEmpty: LiveData<Boolean>
        get() = _isEmpty

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean>
        get() = _isFavorite

    private val _events = MutableLiveData<List<Event>>()
    val events: LiveData<List<Event>>
        get() = _events

    private val _creators = MutableLiveData<List<Creator>>()
    val creators: LiveData<List<Creator>>
        get() = _creators

    private val _characters = MutableLiveData<List<Character>>()
    val characters: LiveData<List<Character>>
        get() = _characters

    private val _stories = MutableLiveData<List<Story>>()
    val stories: LiveData<List<Story>>
        get() = _stories

    private val _series = MutableLiveData<List<Series>>()
    val series: LiveData<List<Series>>
        get() = _series

    private val _type = MutableLiveData<String>()
    val type: LiveData<String>
        get() = _type

    private val _isListEmpty = MutableLiveData<Boolean>()
    val isListEmpty: LiveData<Boolean>
        get() = _isListEmpty

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _price = MutableLiveData<String>()
    val price: LiveData<String>
        get() = _price

    private val _dates = MutableLiveData<List<Date>>()
    val dates: LiveData<List<Date>>
        get() = _dates

    fun initDataDetail(id: Int) {
        getComic(id)
        checkFavorite(id)
    }

    fun getDetailFilterByComic(type: String, idComic: Int) {
        when (type) {
            BUNDLE.BUNDLE_CHARACTER -> {
                getCharactersFilterByComic(idComic)
                _type.value = BUNDLE.BUNDLE_CHARACTER
            }
            BUNDLE.BUNDLE_EVENT -> {
                getEventsFilterByComic(idComic)
                _type.value = BUNDLE.BUNDLE_EVENT
            }
            BUNDLE.BUNDLE_CREATOR -> {
                getCreatorsFilterByComic(idComic)
                _type.value = BUNDLE.BUNDLE_CREATOR
            }
            BUNDLE.BUNDLE_STORY -> {
                getStoriesFilterByComic(idComic)
                _type.value = BUNDLE.BUNDLE_STORY
            }

            BUNDLE.BUNDLE_SERIES -> {
                _type.value = BUNDLE.BUNDLE_SERIES
            }
        }
    }

    private fun getComic(comicId: Int) {
        comicRepository.getComic(comicId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.modified = formatDate(it.modified)
                _comic.value = it
                _isEmpty.value = it.image?.isEmpty()
                _price.value = PRICE + it.prices?.first()?.price + TYPE_PRICE
                formatDate(it.dates[0].date)
                for (i in it.dates.indices) {
                    it.dates[i].date = formatDate(it.dates[i].date)
                }
                _dates.value = it.dates
            }, {
                _error.value
            })
            .addTo(disposables)
    }

    private fun checkFavorite(id: Int) {
        comicRepository.isFavorite(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _isFavorite.value = it
            }, {
                _error.value = it.message.toString()
            })
            .addTo(disposables)
    }

    fun updateFavorite() {
        comic.value?.let { it ->
            if (isFavorite.value == true) {
                comicRepository.deleteFavorite(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        _isFavorite.value = false
                    }, {
                        _error.value = it.message.toString()
                    })
                    .addTo(disposables)
            } else {
                comicRepository.insertFavorite(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        _isFavorite.value = true
                    }, {
                        _error.value = it.message.toString()
                    })
                    .addTo(disposables)
            }
        }
    }

    private fun getCharactersFilterByComic(comicId: Int) {
        _isLoading.value = true
        characterRepository.getCharactersFilterByComic(comicId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _isLoading.value = false
                _characters.value = it
                _isListEmpty.value = it.isEmpty()
            }, {
                _isLoading.value = false
                _error.value = it.message.toString()
            })
            .addTo(disposables)
    }

    private fun getEventsFilterByComic(comicId: Int) {
        _isLoading.value = true
        eventRepository.getEventsFilterByComic(comicId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _isLoading.value = false
                _events.value = it
                _isListEmpty.value = it.isEmpty()
            }, {
                _isLoading.value = false
                _error.value = it.message.toString()
            })
            .addTo(disposables)
    }

    private fun getStoriesFilterByComic(comicId: Int) {
        _isLoading.value = true
        storyRepository.getStoriesFilterByComic(comicId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _isLoading.value = false
                _stories.value = it
                _isListEmpty.value = it.isEmpty()
            }, {
                _isLoading.value = false
                _error.value = it.message.toString()
            })
            .addTo(disposables)
    }

    private fun getCreatorsFilterByComic(comicId: Int) {
        _isLoading.value = true
        creatorRepository.getCreatorsFilterByComic(comicId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _isLoading.value = false
                _creators.value = it
                _isListEmpty.value = it.isEmpty()
            }, {
                _isLoading.value = false
                _error.value = it.message.toString()
            })
            .addTo(disposables)
    }

    @SuppressLint("SimpleDateFormat")
    private fun formatDate(date: String): String {
        val subDate = date.substring(0, SUB_INDEX)
        val inputFormat = SimpleDateFormat(INPUT_FORMAT_DATE)
        val outputFormat = SimpleDateFormat(OUTPUT_FORMAT_DATE)
        val inputDateFormat = inputFormat.parse(subDate)
        return outputFormat.format(inputDateFormat)
    }
}
