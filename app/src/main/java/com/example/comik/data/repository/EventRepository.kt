package com.example.comik.data.repository

import com.example.comik.data.model.Event
import io.reactivex.rxjava3.core.Observable

interface EventRepository {
    fun getEvents(): Observable<List<Event>>
    fun getEventsFilterByComic(comicId: Int): Observable<List<Event>>
}
