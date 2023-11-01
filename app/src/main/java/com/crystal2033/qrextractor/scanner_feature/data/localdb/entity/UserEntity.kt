package com.crystal2033.qrextractor.scanner_feature.data.localdb.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class UserEntity(
    val username: String,
    val password: String,

    @PrimaryKey(autoGenerate = true)
    var userId: Long = 0
)