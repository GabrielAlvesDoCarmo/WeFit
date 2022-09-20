package com.gdsdesenvolvimento.wefit.data.model.db

import androidx.room.Entity

@Entity(tableName = "InfoRepo")
data class InfoRepo(
    val fullName: String,
    val description: String,
    val ownerAvatarUrl: String,
    val stargazersCount: Int,
    val language: String,
    val htmlUrl: String
) {
}