package com.umbingo.umbingoback.models.dto

import java.io.Serializable

data class BingoRegisterDto(
    val name: String,
    val emoji: String,
    val description: String?,
    val words: List<String>,
    val creatorId: String,
    val theme: String,
) : Serializable
