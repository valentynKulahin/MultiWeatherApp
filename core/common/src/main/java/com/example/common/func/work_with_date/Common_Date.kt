package com.example.common.func.work_with_date

import com.example.common.func.work_with_strings.Common_Strings
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

object Common_Date {

    fun getCurrentFormattedDate(): String {
        val currentDate = LocalDate.now()

        // Format the month and day
        val month = currentDate.month.getDisplayName(TextStyle.FULL, Locale.getDefault())
        val day = currentDate.dayOfMonth.toString() + Common_Strings.getDayOfMonthSuffix(currentDate.dayOfMonth)
        val year = currentDate.year

        return "$month, $day $year"
    }

    fun getDayOfWeekFromDate(date: String): String {
        return LocalDate.parse(date).dayOfWeek.toString()
    }

}