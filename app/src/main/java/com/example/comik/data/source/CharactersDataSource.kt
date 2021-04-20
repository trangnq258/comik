package com.example.comik.data.source

import com.example.comik.data.model.CharacterResponse
import io.reactivex.rxjava3.core.Observable

interface CharactersDataSource {
    fun getCharacters(): Observable<CharacterResponse>
    fun getCharactersFilterByComic(comicId: Int): Observable<CharacterResponse>
}
