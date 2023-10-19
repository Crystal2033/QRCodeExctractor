package com.crystal2033.qrextractor.scanner_feature.presentation.uiItems.preview

import androidx.compose.runtime.Composable
import com.crystal2033.qrextractor.scanner_feature.domain.model.Keyboard
import com.crystal2033.qrextractor.scanner_feature.domain.model.Person
import com.crystal2033.qrextractor.scanner_feature.domain.model.QRScannableData
import com.crystal2033.qrextractor.scanner_feature.domain.model.Unknown

@Composable
fun ShowDataItemByType(qrScannable: QRScannableData?) {
    when (qrScannable){
        is Person ->{
            PersonInfo(person = qrScannable)
        }
        is Unknown ->{
            UnknownItem(unknownDataInfo = qrScannable)
        }
        is Keyboard -> {
            KeyboardItem(keyboard = qrScannable)
        }
        else -> {

        }
    }
}