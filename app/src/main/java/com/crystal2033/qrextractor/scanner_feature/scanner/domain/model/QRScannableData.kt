package com.crystal2033.qrextractor.scanner_feature.scanner.domain.model

import com.crystal2033.qrextractor.core.model.DatabaseObjectTypes

/**
Если мы хотим отобразить хоть какие-то данные на превью после сканирования,
мы должны в классе имплементировать этот интерфейс. Он позволяет задавать полиморфное поведение.
Чтобы получить нужное поведение, нужно также обратиться к классам:
 UseCaseGetQRCodeFactory и DatabaseObjectTypes.
 */
interface QRScannableData {
    fun getDatabaseTableName(): DatabaseObjectTypes
    fun getDatabaseID(): Long
}