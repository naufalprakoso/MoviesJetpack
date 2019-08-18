package com.naufalprakoso.moviesjetpack.data.source.local.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tvshows")
data class TvShowEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: String = "",

    @ColumnInfo(name = "title")
    val title: String? = "",

    @ColumnInfo(name = "overview")
    val overview: String? = "",

    @ColumnInfo(name = "rating")
    val rating: String? = "",

    @ColumnInfo(name = "genre")
    val genre: String? = "",

    @ColumnInfo(name = "image")
    val image: String? = "",

    @ColumnInfo(name = "year")
    val year: String? = "",

    @ColumnInfo(name = "episode")
    val episode: String? = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(overview)
        parcel.writeString(rating)
        parcel.writeString(genre)
        parcel.writeString(image)
        parcel.writeString(year)
        parcel.writeString(episode)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TvShowEntity> {
        override fun createFromParcel(parcel: Parcel): TvShowEntity {
            return TvShowEntity(parcel)
        }

        override fun newArray(size: Int): Array<TvShowEntity?> {
            return arrayOfNulls(size)
        }
    }
}