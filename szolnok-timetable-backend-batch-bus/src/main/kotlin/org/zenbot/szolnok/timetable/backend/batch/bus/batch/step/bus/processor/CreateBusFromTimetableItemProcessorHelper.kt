package org.zenbot.szolnok.timetable.backend.batch.bus.batch.step.bus.processor

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.zenbot.szolnok.timetable.backend.domain.batch.Timetable
import org.zenbot.szolnok.timetable.backend.domain.document.bus.Bus
import org.zenbot.szolnok.timetable.backend.repository.BusRepository

@Component
class CreateBusFromTimetableItemProcessorHelper(private val busRepository: BusRepository) {

    private val log = LoggerFactory.getLogger(CreateBusFromTimetableItemProcessorHelper::class.java)

    fun createBusFromTimetable(timetable: Timetable): Bus {
        log.debug("Fetching bus=[#{}] from database", timetable.busName)
        val bus = busRepository.findByBusName(timetable.busName)
        val result: Bus
        if (bus.isPresent) {
            result = bus.get()
            result.busName = timetable.busName
        } else {
            result = Bus()
            result.busName = timetable.busName
        }
        return result
    }
}
