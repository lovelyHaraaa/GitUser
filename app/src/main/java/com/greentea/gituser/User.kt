package com.greentea.gituser

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var name: String,
    var username: String,
    var company: String,
    var location: String,
    var profilePhoto: Int,
    var followers: String,
    var following: String,
    var repositories: String
) : Parcelable
