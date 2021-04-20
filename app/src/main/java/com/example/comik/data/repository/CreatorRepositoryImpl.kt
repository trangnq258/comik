package com.example.comik.data.repository

import com.example.comik.data.model.Creator
import com.example.comik.data.source.CreatorsDataSource
import io.reactivex.rxjava3.core.Observable

class CreatorRepositoryImpl(
    private val remote: CreatorsDataSource
) : CreatorRepository {

    override fun getCreators(): Observable<List<Creator>> = remote.getCreators().map {
        it.data.results
    }

    override fun getCreatorsFilterByComic(comicId: Int): Observable<List<Creator>> =
        remote.getCreatorsFilterByComic(comicId).map { it.data.results }
}
