package org.zenbot.szolnok.timetable.configuration

import org.springframework.batch.core.Job
import org.springframework.batch.core.JobExecutionListener
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.zenbot.szolnok.timetable.batch.listener.RemoveBusRoutesExecutionListener
import org.zenbot.szolnok.timetable.configuration.properties.TimetableProperties
import org.zenbot.szolnok.timetable.dao.BusRepository
import org.zenbot.szolnok.timetable.dao.BusStopRepository

@Configuration
@EnableBatchProcessing
@EnableConfigurationProperties(TimetableProperties::class)
open class BatchConfiguration(private val jobBuilderFactory: JobBuilderFactory, private val timetableProperties: TimetableProperties, private val readUrlsStep: Step, private val saveBusStep: Step) {

    @Bean
    open fun importTimetableJob(busRepository: BusRepository, busStopRepository: BusStopRepository): Job {
        return jobBuilderFactory
                .get("importTimetableJob")
                .listener(jobExecutionListener(busRepository, busStopRepository))
                .start(readUrlsStep)
                .next(saveBusStep)
                .build()
    }

    @Bean
    open fun jobExecutionListener(busRepository: BusRepository, busStopRepository: BusStopRepository): JobExecutionListener {
        return RemoveBusRoutesExecutionListener(busRepository, busStopRepository, timetableProperties.resource!!)
    }
}