package com.crystal2033.qrextractor.scanner_feature.data.localdb.entity

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithScannedGroupsAndObjects(
    @Embedded val user: UserEntity,
    @Relation(
        entity = ScannedGroupEntity::class,
        parentColumn = "userId",
        entityColumn = "userCreatorId"
    )

    val scannedGroupWithScannedObjects: List<ScannedGroupWithScannedObjects>
)