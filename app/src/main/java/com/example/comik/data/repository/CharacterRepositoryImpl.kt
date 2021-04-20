package com.example.comik.data.repository

import com.example.comik.data.model.Character
import com.example.comik.data.source.CharactersDataSource
import io.reactivex.rxjava3.core.Observable

class CharacterRepositoryImpl(
    private val remote: CharactersDataSource
) : CharacterRepository {

    override fun getCharacters(): Observable<List<Character>> =
        remote.getCharacters().map { it.data.results }

    override fun getCharactersFilterByComic(comicId: Int): Observable<List<Character>> =
        remote.getCharactersFilterByComic(comicId).map { it.data.results }
}
