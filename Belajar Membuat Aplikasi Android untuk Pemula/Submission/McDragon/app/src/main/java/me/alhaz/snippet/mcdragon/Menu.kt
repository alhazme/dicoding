package me.alhaz.snippet.mcdragon

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Menu (
    var photo: String = "",
    var name: String = "",
    var description: String = "",
    var price: Int = 0
) : Parcelable