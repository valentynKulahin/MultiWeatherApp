package com.example.common.func.work_with_url

object CommonURL {

    fun convertStringToLink(string: String): String {
        return "https:" + string.replace("//", "")
    }

}