package com.umbingo.umbingoback.controllers

import com.umbingo.umbingoback.models.dto.BingoFeedDto
import com.umbingo.umbingoback.models.entities.Bingo
import com.umbingo.umbingoback.models.repositories.BingoRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class FeedController(val repository: BingoRepository) {
    @GetMapping("/feed")
    fun index(): List<BingoFeedDto> =
        repository.findAll().mapNotNull { it.toFeedDto() }

    @GetMapping("/feed/{id}")
    fun allByCreator(@PathVariable id: String): List<BingoFeedDto> =
        repository.findByCreatorId(id).mapNotNull { it.toFeedDto() }

    fun Bingo.toFeedDto(): BingoFeedDto? {
        return if(this.words != null && this.id != null) {
            BingoFeedDto(id = this.id!!, name = this.name, keyWords = this.words!!.take(3), theme = this.theme)
        } else {
            null
        }
    }
 }