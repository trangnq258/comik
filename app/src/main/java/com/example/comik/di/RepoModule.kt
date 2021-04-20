package com.example.comik.di

import androidx.room.Room
import com.example.comik.data.repository.*
import com.example.comik.data.source.*
import com.example.comik.data.source.local.ComicLocalDataSource
import com.example.comik.data.source.local.database.AppDatabase
import com.example.comik.data.source.local.database.AppDatabase.Companion.DATABASE_NAME
import com.example.comik.data.source.remote.*
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dbModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<AppDatabase>().comicDao() }
}

val repositoryModule = module {
    single<ComicsDataSource.Local> { ComicLocalDataSource(get()) }
    single<ComicsDataSource.Remote> { ComicRemoteDataSource(get()) }
    single<ComicRepository> { ComicRepositoryImpl(get(), get()) }
    single<EventsDataSource> { EventRemoteDataSource(get()) }
    single<EventRepository> { EventRepositoryImpl(get()) }
    single<CreatorsDataSource> { CreatorRemoteDataSource(get()) }
    single<CreatorRepository> { CreatorRepositoryImpl(get()) }
    single<CharactersDataSource> { CharacterRemoteDataSource(get()) }
    single<CharacterRepository> { CharacterRepositoryImpl(get()) }
    single<StoriesDataSource> { StoryRemoteDataSource(get()) }
    single<StoryRepository> { StoryRepositoryImpl(get()) }
    single<SeriesDataSource> { SeriesRemoteDataSource(get()) }
    single<SeriesRepository> { SeriesRepositoryImpl(get()) }
}
