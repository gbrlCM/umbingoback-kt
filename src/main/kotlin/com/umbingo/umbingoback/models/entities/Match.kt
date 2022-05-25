package com.umbingo.umbingoback.models.entities
import java.util.*
import javax.persistence.*

@Table(name = "match")
@Entity
class Match (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    val id: UUID?,

    @Column(name = "room_code", unique = true, nullable = false)
    val roomCode: String,

    @ManyToOne(optional = false)
    @JoinColumn(name = "bingo_id", nullable = false)
    var bingo: Bingo
    )