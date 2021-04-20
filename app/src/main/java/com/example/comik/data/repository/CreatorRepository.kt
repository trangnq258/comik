package com.example.comik.data.repository

import com.example.comik.data.model.Creator
import io.reactivex.rxjava3.core.Observable

interface CreatorRepository {
    fun getCreators(): Observable<List<Creator>>
    fun getCreatorsFilterByComic(comicId: Int): Observable<List<Creator>>
}
