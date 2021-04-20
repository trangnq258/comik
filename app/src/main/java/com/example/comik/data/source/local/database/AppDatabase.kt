package com.example.comik.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.comik.data.model.Comic
import com.example.comik.data.source.local.database.AppDatabase.Companion.DATABASE_VERSION
import com.example.comik.data.source.local.database.AppDatabase.Companion.EXPORT_SCHEME
import com.example.comik.data.source.local.database.dao.ComicDao
import com.example.comik.utils.ImageTypeConverter

@Database(
    entities = [Comic::class],
    version = DATABASE_VERSION,
    exportSchema = EXPORT_SCHEME
)

@TypeConverters(ImageTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun comicDao(): ComicDao

    companion object {
        const val DATABASE_VERSION = 3
        const val DATABASE_NAME = "comicFavorite"
        const val EXPORT_SCHEME = false
    }
}
