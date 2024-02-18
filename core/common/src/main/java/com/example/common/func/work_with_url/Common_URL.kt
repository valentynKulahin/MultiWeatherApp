package com.example.common.func.work_with_url

object Common_URL {

    fun convertStringToLink(string: String): String {
        return "https:" + string.replace("//", "")
    }

}