package com.example.comik.data.source.remote

import com.example.comik.data.model.StoryResponse
import com.example.comik.data.source.StoriesDataSource
import com.example.comik.data.source.remote.utlis.APIService
import io.reactivex.rxjava3.core.Observable

class StoryRemoteDataSource(
    private val apiService: APIService
) : StoriesDataSource {

    override fun getStories() = apiService.getStories()
    override fun getStoriesFilterByComic(comicId: Int) = apiService.getStoriesFilterByComic(comicId)
}
