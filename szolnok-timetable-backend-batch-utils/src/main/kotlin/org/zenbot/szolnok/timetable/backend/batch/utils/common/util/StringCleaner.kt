package org.zenbot.szolnok.timetable.backend.batch.utils.common.util

import org.springframework.stereotype.Component

@Component
class StringCleaner {
    fun clean(string: String): String {
        return string
                .replace("û".toRegex(), "ű")
                .replace("õ".toRegex(), "ő")
                .replace("ô".toRegex(), "ő")
                .replace("\\.".toRegex(), "")
                .trim { it <= ' ' }
                .replace(" +".toRegex(), " ")
    }
}
