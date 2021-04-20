package com.example.comik.data.repository

import com.example.comik.data.model.Story
import io.reactivex.rxjava3.core.Observable

interface StoryRepository {
    fun getStories(): Observable<List<Story>>
    fun getStoriesFilterByComic(comicId: Int): Observable<List<Story>>
}
