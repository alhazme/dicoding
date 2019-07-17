package me.alhaz.snippet.academies.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ContentEntity (
    var mContent: String = ""
) : Parcelable