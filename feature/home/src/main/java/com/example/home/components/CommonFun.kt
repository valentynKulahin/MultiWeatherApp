package com.example.home.components

import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

object CommonFun {

    fun getCurrentFormattedDate(): String {
        val currentDate = LocalDate.now()

        // Format the month and day
        val month = currentDate.month.getDisplayName(TextStyle.FULL, Locale.getDefault())
        val day = currentDate.dayOfMonth.toString() + getDayOfMonthSuffix(currentDate.dayOfMonth)
        val year = currentDate.year

        return "$month, $day $year"
    }

    private fun getDayOfMonthSuffix(day: Int): String {
        return when (day) {
            1, 21, 31 -> "st"
            2, 22 -> "nd"
            3, 23 -> "rd"
            else -> "th"
        }
    }

    fun getDayOfWeekFromDate(date: String): String {
        return LocalDate.parse(date).dayOfWeek.toString()
    }

    fun convertStringToLink(string: String): String {
        return "https:" + string.replace("//", "")
    }

}