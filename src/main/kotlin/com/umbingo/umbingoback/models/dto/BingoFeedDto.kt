package com.umbingo.umbingoback.models.dto

import java.io.Serializable
import java.util.*

data class BingoFeedDto(val id: UUID?,
                        val name: String?,
                        val keyWords: List<String>,
                        val theme: String) : Serializable
