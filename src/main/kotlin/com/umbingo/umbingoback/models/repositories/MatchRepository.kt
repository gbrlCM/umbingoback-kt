package com.umbingo.umbingoback.models.repositories

import com.umbingo.umbingoback.models.entities.Match
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MatchRepository : JpaRepository<Match, UUID> {
    fun findByRoomCode(roomCode: String): Match?
}