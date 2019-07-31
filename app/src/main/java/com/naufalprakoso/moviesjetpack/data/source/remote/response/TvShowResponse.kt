package com.naufalprakoso.moviesjetpack.data.source.remote.response

import android.os.Parcel
import android.os.Parcelable

data class TvShowResponse(
    var id: String? = "",
    val title: String? = "",
    val overview: String? = "",
    val rating: String? = "",
    val genre: String? = "",
    val image: String? = "",
    val year: String? = "",
    val episode: String? = ""
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
        parcel.writeString(episode)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TvShowResponse> {
        override fun createFromParcel(parcel: Parcel): TvShowResponse {
            return TvShowResponse(parcel)
        }

        override fun newArray(size: Int): Array<TvShowResponse?> {
            return arrayOfNulls(size)
        }
    }
}