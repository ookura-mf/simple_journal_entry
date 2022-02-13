package com.okeicalm.simpleJournalEntry.infra.db.dao

import com.okeicalm.simpleJournalEntry.infra.db.table.JournalEntries
import com.okeicalm.simpleJournalEntry.infra.db.table.Journals
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class JournalDao(id: EntityID<Long>): LongEntity(id) {
    companion object : LongEntityClass<JournalDao>(Journals)
    var incurredOn  by Journals.incurredOn
    val journalEntries by JournalEntryDao referrersOn JournalEntries.journal
}
