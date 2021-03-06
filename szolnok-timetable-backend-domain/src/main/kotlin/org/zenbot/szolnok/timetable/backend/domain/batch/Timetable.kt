package org.zenbot.szolnok.timetable.backend.domain.batch

data class Timetable(
    var busName: String = "",
    var startBusStopName: String = "",
    var endBusStopName: String = "",
    var activeStopName: String = "",
    var timetable: MutableMap<Int, Map<String, String>> = HashMap()
) {

    fun addRow(hour: Int, values: Map<String, String>) {
        timetable[hour] = values
    }
}