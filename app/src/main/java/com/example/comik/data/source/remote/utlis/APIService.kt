package com.example.comik.data.source.remote.utlis

import com.example.comik.data.model.*
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {
    @GET("comics")
    fun getComics(): Observable<ComicResponse>

    @GET("events")
    fun getEvents(): Observable<EventResponse>

    @GET("creators")
    fun getCreators(): Observable<CreatorResponse>

    @GET("comics")
    fun getComicsByYear(
        @Query("format") format: String,
        @Query("startYear") year: Int
    ): Observable<ComicResponse>

    @GET("characters")
    fun getCharacters(): Observable<CharacterResponse>

    @GET("series")
    fun getSeries(): Observable<SeriesResponse>

    @GET("stories")
    fun getStories(): Observable<StoryResponse>

    @GET("characters/{character_id}/comics")
    fun getComicsByCharacter(@Path("character_id") characterId: Int): Observable<ComicResponse>

    @GET("events/{event_id}/comics")
    fun getComicsByEvent(@Path("event_id") eventId: Int): Observable<ComicResponse>

    @GET("series/{series_id}/comics")
    fun getComicsBySeries(@Path("series_id") seriesId: Int): Observable<ComicResponse>

    @GET("creators/{creator_id}/comics")
    fun getComicsByCreators(@Path("creator_id") creatorId: Int): Observable<ComicResponse>

    @GET("stories/{story_id}/comics")
    fun getComicsByStory(@Path("story_id") storyId: Int): Observable<ComicResponse>

    @GET("comics/{comic_id}")
    fun getComic(@Path("comic_id") comicId: Int): Observable<ComicResponse>

    @GET("comics/{comic_id}/characters")
    fun getCharactersFilterByComic(@Path("comic_id") comicId: Int): Observable<CharacterResponse>

    @GET("comics/{comic_id}/events")
    fun getEventsFilterByComic(@Path("comic_id") comicId: Int): Observable<EventResponse>

    @GET("comics/{comic_id}/creators")
    fun getCreatorsFilterByComic(@Path("comic_id") comicId: Int): Observable<CreatorResponse>

    @GET("comics/{comic_id}/stories")
    fun getStoriesFilterByComic(@Path("comic_id") comicId: Int): Observable<StoryResponse>
}
