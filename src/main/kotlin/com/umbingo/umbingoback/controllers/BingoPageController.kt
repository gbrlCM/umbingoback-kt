package com.umbingo.umbingoback.controllers

import com.umbingo.umbingoback.models.dto.BingoPageDto
import com.umbingo.umbingoback.models.entities.Bingo
import com.umbingo.umbingoback.models.entities.Match
import com.umbingo.umbingoback.models.repositories.BingoRepository
import com.umbingo.umbingoback.models.repositories.MatchRepository
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.HttpClientErrorException
import java.util.*

@RestController
class BingoPageController(val bingoRepository: BingoRepository, val matchRepository: MatchRepository) {

    @GetMapping("/bingo/{id}")
    fun getBingo(@PathVariable id: UUID): BingoPageDto {
        val bingoRegistry = bingoRepository.findById(id)
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

    @GetMapping("/bingo/{id}/match/create")
    fun createMatch(@PathVariable id: UUID): String {
        val bingoRegistry = bingoRepository.findById(id)
            .orElseThrow {
                throw HttpClientErrorException.create(
                    HttpStatus.NOT_FOUND,
                    "not found",
                    HttpHeaders(),
                    ByteArray(0),
                    null
                )
            }
        val matchCount = matchRepository.count()
        var matchCode = matchCount.toString(16)
        if (matchCode.length < 6) {
            val addedValue = 6 - matchCode.length
            val prepend = "0".repeat(addedValue)
            matchCode = prepend + matchCode
        }

        val match = Match(null, matchCode, bingoRegistry)
        matchRepository.save(match)

        return match.roomCode
    }

    @GetMapping("/bingo/match/{id}/enter")
    fun enterMatch(@PathVariable id: String): BingoPageDto? {
        matchRepository.findByRoomCode(id)?.let {
            return it.bingo.toBingoPageDTO()
        }
        throw HttpClientErrorException.create(
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