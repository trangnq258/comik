package com.example.comik.data.source.remote

import com.example.comik.data.model.CreatorResponse
import com.example.comik.data.source.CreatorsDataSource
import com.example.comik.data.source.remote.utlis.APIService
import io.reactivex.rxjava3.core.Observable

class CreatorRemoteDataSource(
    private val apiService: APIService
) : CreatorsDataSource {

    override fun getCreators() = apiService.getCreators()
    override fun getCreatorsFilterByComic(comicId: Int) = apiService.getCreatorsFilterByComic(comicId)
}
