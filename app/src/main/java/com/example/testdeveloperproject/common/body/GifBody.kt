package com.example.testdeveloperproject.common.body

import android.os.Parcel
import android.os.Parcelable
import com.example.domain.model.ImageModel

class GifBody(
    val id: String,
    val url: String?,
    val images: ImageModel,
    var isSelectedGif: Boolean = false
): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString(),
        TODO("images")
    ) {
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<GifBody> {
        override fun createFromParcel(parcel: Parcel): GifBody {
            return GifBody(parcel)
        }

        override fun newArray(size: Int): Array<GifBody?> {
            return arrayOfNulls(size)
        }
    }

}