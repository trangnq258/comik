package com.example.comik.data.source.remote

import com.example.comik.data.model.EventResponse
import com.example.comik.data.source.EventsDataSource
import com.example.comik.data.source.remote.utlis.APIService
import io.reactivex.rxjava3.core.Observable

class EventRemoteDataSource(
    private val apiService: APIService
) : EventsDataSource {

    override fun getEvents() = apiService.getEvents()
    override fun getEventsFilterByComic(comicId: Int) = apiService.getEventsFilterByComic(comicId)
}
