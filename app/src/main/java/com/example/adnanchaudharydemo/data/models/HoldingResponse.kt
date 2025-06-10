package com.example.adnanchaudharydemo.data.models

import com.google.gson.annotations.SerializedName

data class HoldingResponse(
    @SerializedName("data")
    val data: UserHoldingDataDto?
)
