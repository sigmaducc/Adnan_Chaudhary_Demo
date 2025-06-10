package com.example.adnanchaudharydemo.data.models

import com.google.gson.annotations.SerializedName

data class UserHoldingDataDto(
    @SerializedName("userHolding")
    val userHolding: List<UserHoldingDto>?
)