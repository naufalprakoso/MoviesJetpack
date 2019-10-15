package com.naufalprakoso.moviesjetpack.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "title")
    val title: String? = "",

    @ColumnInfo(name = "overview")
    val overview: String? = "",

    @ColumnInfo(name = "vote_average")
    val vote_average: Double? = 0.0,

    @ColumnInfo(name = "poster_path")
    val poster_path: String? = "",

    @ColumnInfo(name = "release_date")
    val release_date: String? = "",

    @ColumnInfo(name = "vote_count")
    val vote_count: Int? = 0
)