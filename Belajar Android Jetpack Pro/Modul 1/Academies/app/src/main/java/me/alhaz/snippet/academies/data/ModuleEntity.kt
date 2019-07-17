package me.alhaz.snippet.academies.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ModuleEntity (
    var mModuleId: String = "",
    var mCourseI: String = "",
    var mTitle: String = "",
    var mPosition: Int = 0,
    var mRead: Boolean = false
) : Parcelable