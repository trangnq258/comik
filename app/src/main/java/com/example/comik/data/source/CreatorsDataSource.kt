package com.example.comik.data.source

import com.example.comik.data.model.CreatorResponse
import io.reactivex.rxjava3.core.Observable

interface CreatorsDataSource {
    fun getCreators(): Observable<CreatorResponse>
    fun getCreatorsFilterByComic(comicId: Int): Observable<CreatorResponse>
}
