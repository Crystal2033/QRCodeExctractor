package com.crystal2033.qrextractor.add_object_feature.qr_codes_document.presentation.vm_view_communication

sealed class UIDocumentQRCodeStickersEvent {
    data class OnFileCreatedSuccessfully(val fullFilePath: String) : UIDocumentQRCodeStickersEvent()
}