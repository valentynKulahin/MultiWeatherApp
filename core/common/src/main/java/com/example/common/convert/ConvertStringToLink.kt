package com.example.common.convert

fun convertStringToLink(string: String): String {
    return "https:" + string.replace("//", "")
}