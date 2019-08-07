package com.naufalprakoso.moviesjetpack.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.naufalprakoso.moviesjetpack.data.source.local.entity.MovieEntity
import com.naufalprakoso.moviesjetpack.data.source.local.entity.TvShowEntity

@Database(
    entities = [MovieEntity::class, TvShowEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase(){
    companion object{
        private var INSTANCE: MovieDatabase? = null
        private val sLock = Object()

        fun getInstance(context: Context): MovieDatabase? {
            synchronized(sLock){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        MovieDatabase::class.java, "Movies.db").build()
                }
            }

            return INSTANCE
        }
    }
}