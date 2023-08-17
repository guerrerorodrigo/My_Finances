package com.rodrigoguerrero.myfinances.android.ui.common.annotations

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview


@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    group = "Phone",
    device = Devices.PIXEL_3A_XL,
)
@Preview(showBackground = true, group = "Phone", device = Devices.PIXEL_3A_XL)
annotation class PhonePreviews
