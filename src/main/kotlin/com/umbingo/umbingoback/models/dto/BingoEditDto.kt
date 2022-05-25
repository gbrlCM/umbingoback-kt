package com.umbingo.umbingoback.models.dto

import java.io.Serializable
import java.util.*

data class BingoEditDto(
    val id: UUID?,
    val name: String?,
    val emoji: String?,
    val description: String?,
    val words: List<String>?,
    val theme: String?
) : Serializable
