package com.example.eunoia.common.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.toLocalDateTime

object OffsetDTHelper {
    fun Instant.isToday(): Boolean {
        val localDate = this.toLocalDateTime(TimeZone.currentSystemDefault()).date
        return localDate == Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
    }

    fun Instant.isYesterday(): Boolean {
        val localDate = this.toLocalDateTime(TimeZone.currentSystemDefault()).date
        return localDate == Clock.System.now()
            .toLocalDateTime(TimeZone.currentSystemDefault()).date.minus(1, DateTimeUnit.DAY)
    }


}