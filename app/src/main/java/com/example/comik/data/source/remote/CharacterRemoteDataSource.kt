package com.example.comik.data.source.remote

import com.example.comik.data.model.CharacterResponse
import com.example.comik.data.source.CharactersDataSource
import com.example.comik.data.source.remote.utlis.APIService
import io.reactivex.rxjava3.core.Observable

class CharacterRemoteDataSource(
    private val apiService: APIService
) : CharactersDataSource {

    override fun getCharacters() = apiService.getCharacters()
    override fun getCharactersFilterByComic(comicId: Int) = apiService.getCharactersFilterByComic(comicId)
}
