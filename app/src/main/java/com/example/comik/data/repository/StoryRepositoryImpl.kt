package com.example.comik.data.repository

import com.example.comik.data.model.Story
import com.example.comik.data.source.StoriesDataSource
import io.reactivex.rxjava3.core.Observable

class StoryRepositoryImpl(
    private val remote: StoriesDataSource
) : StoryRepository {

    override fun getStories(): Observable<List<Story>> = remote.getStories().map { it.data.results }
    override fun getStoriesFilterByComic(comicId: Int): Observable<List<Story>> =
        remote.getStories().map { it.data.results }
}
