package com.example.testdeveloperproject.common.body

import android.os.Parcel
import android.os.Parcelable
import com.example.domain.model.Gif

class GifsBody() : ArrayList<GifBody>(), Parcelable {
    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GifsBody> {
        override fun createFromParcel(parcel: Parcel): GifsBody {
            return GifsBody(parcel)
        }

        override fun newArray(size: Int): Array<GifsBody?> {
            return arrayOfNulls(size)
        }
    }
}