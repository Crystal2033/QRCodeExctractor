package com.crystal2033.qrextractor.scanner_feature.scanner.data.localdb.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.crystal2033.qrextractor.scanner_feature.scanned_info_list.domain.model.ScannedGroup

@Entity(tableName = "ScannedGroup")
data class ScannedGroupEntity(
    val groupName: String,
    //val date: LocalDate,
    val userCreatorId: Long,

    @PrimaryKey(autoGenerate = true)
    var scannedGroupId: Long= 0
){
}