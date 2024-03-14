package com.example.common.func.work_with_strings

object CommonStrings {

    fun getDayOfMonthSuffix(day: Int): String {
        return when (day) {
            1, 21, 31 -> "st"
            2, 22 -> "nd"
            3, 23 -> "rd"
            else -> "th"
        }
    }

}