package com.gdsdesenvolvimento.wefit.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "InfoRepo")
data class InfoRepo(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "first_name")val fullName: String,
    @ColumnInfo(name = "description")val description: String? = "not-description",
    @ColumnInfo(name = "owner_avatar_url")val ownerAvatarUrl: String,
    @ColumnInfo(name = "stargazers_count") val stargazersCount: Int,
    @ColumnInfo(name = "language")val language: String? = "not-language",
    @ColumnInfo(name = "html_url")val htmlUrl: String
) {
}