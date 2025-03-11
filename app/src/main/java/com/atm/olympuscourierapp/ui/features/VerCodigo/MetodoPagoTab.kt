package com.jjmf.android.olympuscourierapp.ui.features.VerCodigo

import androidx.compose.ui.graphics.Color

enum class MetodoPagoTab(val url: String, val color: Color) {
    Yape(
        "00020101021139320b548c388ab05a49b0a0f82bb2f45dbf5204561153036045802PE5906YAPERO6004Lima6304C4F4",
        Color(0xFF720E9E)
    ),
    Plin(
        "0002015802PE265600323d645b1012cf4c4ea6ad5c2a24de8be60116Plin Network P2P0102115204482953036045912P2P Transfer6004Lima6304B2CB",
        Color(0xFF59BEBB)
    )
}