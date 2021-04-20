package com.example.comik.data.source

import com.example.comik.data.model.StoryResponse
import io.reactivex.rxjava3.core.Observable

interface StoriesDataSource {
    fun getStories(): Observable<StoryResponse>
    fun getStoriesFilterByComic(comicId: Int): Observable<StoryResponse>
}
