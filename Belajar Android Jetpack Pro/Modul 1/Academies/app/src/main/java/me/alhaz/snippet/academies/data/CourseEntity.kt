package me.alhaz.snippet.academies.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CourseEntity(
    var courseId: String = "",
    var title: String = "",
    var description: String = "",
    var deadline: String = "",
    var bookmarked: Boolean = false,
    var imagePath: String = ""
) : Parcelable