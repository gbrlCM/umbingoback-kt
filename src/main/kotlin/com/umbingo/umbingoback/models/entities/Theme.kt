package com.umbingo.umbingoback.models.entities

enum class Theme {
    MAGICPURPLE,
    LIVELYBROWN,
    DEEPBLUE,
    HELPFULGREEN,
    PRODUCTRED;

    companion object {
        fun toString(theme: Theme): String {
            return when(theme) {
                MAGICPURPLE -> "magicPurple"
                LIVELYBROWN -> "livelyBrown"
                DEEPBLUE -> "deepBlue"
                HELPFULGREEN -> "helpfulGreen"
                PRODUCTRED -> "productRed"
            }
        }

        fun fromString(theme: String): Theme? {
            return when(theme) {
                "magicPurple" -> MAGICPURPLE
                "livelyBrown" -> LIVELYBROWN
                "deepBlue" -> DEEPBLUE
                "helpfulGreen" -> HELPFULGREEN
                "productRed" -> PRODUCTRED
                else -> null
            }
        }
    }
}