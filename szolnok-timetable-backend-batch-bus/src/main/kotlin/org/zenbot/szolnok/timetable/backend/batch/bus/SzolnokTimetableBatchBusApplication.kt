package org.zenbot.szolnok.timetable.backend.batch.bus

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SzolnokTimetableBatchBusApplication

fun main(args: Array<String>) {
    runApplication<SzolnokTimetableBatchBusApplication>(*args)
}
