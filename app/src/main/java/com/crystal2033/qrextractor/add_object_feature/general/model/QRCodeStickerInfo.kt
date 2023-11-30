package com.crystal2033.qrextractor.add_object_feature.general.model

import android.graphics.Bitmap
import androidx.compose.ui.graphics.ImageBitmap
import com.crystal2033.qrextractor.scanner_feature.scanner.domain.model.ScannedTableNameAndId

data class QRCodeStickerInfo(
    var qrCode: ImageBitmap? = null,
    var essentialName: String = "",
    var inventoryNumber: String = "",
    //val scannedTableNameAndId: ScannedTableNameAndId? = null
)