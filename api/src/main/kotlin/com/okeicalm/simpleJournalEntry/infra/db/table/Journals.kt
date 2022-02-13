package com.okeicalm.simpleJournalEntry.infra.db.table

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.jodatime.date

object Journals : LongIdTable() {
    val incurredOn = date("incurred_on")
}
