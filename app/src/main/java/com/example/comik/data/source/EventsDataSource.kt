package com.example.comik.data.source

import com.example.comik.data.model.EventResponse
import io.reactivex.rxjava3.core.Observable

interface EventsDataSource {
    fun getEvents(): Observable<EventResponse>
    fun getEventsFilterByComic(comicId: Int): Observable<EventResponse>
}
