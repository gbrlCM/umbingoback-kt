package com.umbingo.umbingoback.controllers

import com.umbingo.umbingoback.models.dto.BingoEditDto
import com.umbingo.umbingoback.models.dto.BingoRegisterDto
import com.umbingo.umbingoback.models.entities.Bingo
import com.umbingo.umbingoback.models.repositories.BingoRepository
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException

@RestController
class RegisterController(val repository: BingoRepository) {

    @PostMapping("/register")
    fun register(@RequestBody bingo: BingoRegisterDto): HttpStatus {
        val bingoRegistry = Bingo(
            id = null,
            name = bingo.name,
            emoji = bingo.emoji,
            description = bingo.description,
            words = bingo.words.toTypedArray(),
            creatorId = bingo.creatorId,
            theme = bingo.theme
        )

        repository.save(bingoRegistry)
        return HttpStatus.ACCEPTED
    }

    @PatchMapping("/edit")
    fun edit(@RequestBody bingo: BingoEditDto): HttpStatus {
        val id = bingo.id ?: return HttpStatus.NOT_FOUND
        var bingoRegistry = repository.findById(id)
            .orElseThrow {
                throw HttpClientErrorException.create(HttpStatus.NOT_FOUND,
                    "not found",
                    HttpHeaders(),
                    ByteArray(0),
                    null)
            }

        bingoRegistry.name = bingo.name ?: bingoRegistry.name
        bingoRegistry.emoji = bingo.emoji ?: bingoRegistry.emoji
        bingoRegistry.description = bingo.description ?: bingoRegistry.description
        bingoRegistry.theme = bingo.theme ?: bingoRegistry.theme
        bingoRegistry.words = bingo.words?.toTypedArray() ?: bingoRegistry.words

        repository.save(bingoRegistry)
        return HttpStatus.ACCEPTED
    }
}