package com.example.comik.data.repository

import com.example.comik.data.model.Event
import com.example.comik.data.source.EventsDataSource
import io.reactivex.rxjava3.core.Observable

class EventRepositoryImpl(
    private val remote: EventsDataSource
) : EventRepository {

    override fun getEvents(): Observable<List<Event>> {
        return remote.getEvents().map {
            it.data.results
        }
    }

    override fun getEventsFilterByComic(comicId: Int): Observable<List<Event>> =
        remote.getEventsFilterByComic(comicId).map { it.data.results }
}
