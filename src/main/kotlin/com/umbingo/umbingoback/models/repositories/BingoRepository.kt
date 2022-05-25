package com.umbingo.umbingoback.models.repositories

import com.umbingo.umbingoback.models.entities.Bingo
import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

@Repository
interface BingoRepository : JpaRepository<Bingo, UUID> {
    fun findByCreatorId(creatorId: String): List<Bingo>
}