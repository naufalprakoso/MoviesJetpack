package com.naufalprakoso.moviesjetpack.data.source.local.entity

import android.os.Parcel
import android.os.Parcelable

data class MovieEntity(
    var id: String? = "",
    val title: String? = "",
    val overview: String? = "",
    val rating: String? = "",
    val genre: String? = "",
    val image: String? = "",
    val year: String? = "",
    val duration: String? = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
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
        parcel.writeString(duration)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieEntity> {
        override fun createFromParcel(parcel: Parcel): MovieEntity {
            return MovieEntity(parcel)
        }

        override fun newArray(size: Int): Array<MovieEntity?> {
            return arrayOfNulls(size)
        }
    }
}