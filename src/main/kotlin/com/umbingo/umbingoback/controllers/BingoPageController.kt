package com.umbingo.umbingoback.controllers

import com.umbingo.umbingoback.models.dto.BingoPageDto
import com.umbingo.umbingoback.models.entities.Bingo
import com.umbingo.umbingoback.models.repositories.BingoRepository
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.HttpClientErrorException
import java.util.*

@RestController
class BingoPageController(val repository: BingoRepository) {

    @GetMapping("/bingo/{id}")
    fun getBingo(@PathVariable id: UUID): BingoPageDto {
        val bingoRegistry = repository.findById(id)
            .orElseThrow {
                throw HttpClientErrorException.create(
                    HttpStatus.NOT_FOUND,
                    "not found",
                    HttpHeaders(),
                    ByteArray(0),
                    null
                )
            }

        return bingoRegistry.toBingoPageDTO()
            ?: throw HttpClientErrorException.create(
                HttpStatus.NOT_FOUND,
                "not found",
                HttpHeaders(),
                ByteArray(0),
                null
            )
    }

    fun Bingo.toBingoPageDTO(): BingoPageDto? {
        val id = this.id ?: return null
        val words = this.words?.toList() ?: return null
        return BingoPageDto(
            id = id,
            name = this.name,
            emoji = this.emoji,
            description = this.description,
            words = words,
            theme = this.theme
        )
    }
}