package com.example.comik.data.repository

import com.example.comik.data.model.Character
import io.reactivex.rxjava3.core.Observable

interface CharacterRepository {
    fun getCharacters(): Observable<List<Character>>
    fun getCharactersFilterByComic(comicId: Int): Observable<List<Character>>
}
