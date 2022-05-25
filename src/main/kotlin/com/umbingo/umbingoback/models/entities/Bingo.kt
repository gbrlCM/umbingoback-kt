package com.umbingo.umbingoback.models.entities
import com.vladmihalcea.hibernate.type.array.StringArrayType
import org.hibernate.annotations.*
import java.util.*
import javax.persistence.*
import javax.persistence.Entity
import javax.persistence.Table

@TypeDefs(
    TypeDef(name = "string-array", typeClass = StringArrayType::class)
)
@Table(name = "bingo")
@Entity
class Bingo (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    val id: UUID?,

    @Column(name = "name", nullable = false, unique = false)
    var name: String,

    @Column(name = "emoji", nullable = true, unique = false)
    var emoji: String?,

    @Column(name = "description", nullable = true)
    var description: String?,

    @Type(type = "string-array")
    @Column(name = "words", nullable = false, columnDefinition = "text[]")
    var words: Array<String>? = arrayOf(),

    @Column(name = "creator_id", nullable = false)
    var creatorId: String,

    @Column(name = "theme", nullable = false)
    var theme: String,

    @Column(name = "match_count", nullable = false)
    var matchCount: Int = 0,

    @Column(name = "created_at", nullable = false)
    var createdAt: Date = Date(),

    @Column(name = "updated_at", nullable = true)
    var updatedAt: Date? = null,
)