package com.example.testdeveloperproject.common.model

import android.os.Parcel
import android.os.Parcelable
import com.example.domain.model.ImageModel

class GifModel(
    val id: String,
    val url: String?,
    val image: ImageModel? = null
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString(),
        TODO("images")
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GifModel> {
        override fun createFromParcel(parcel: Parcel): GifModel {
            return GifModel(parcel)
        }

        override fun newArray(size: Int): Array<GifModel?> {
            return arrayOfNulls(size)
        }
    }
}