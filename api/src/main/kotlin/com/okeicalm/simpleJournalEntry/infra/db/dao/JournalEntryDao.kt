package com.okeicalm.simpleJournalEntry.infra.db.dao

import com.okeicalm.simpleJournalEntry.infra.db.table.JournalEntries
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class JournalEntryDao(id: EntityID<Long>): LongEntity(id) {
    companion object : LongEntityClass<JournalEntryDao>(JournalEntries)
    var journal by JournalDao referencedOn JournalEntries.journal
    var side by JournalEntries.side
    var account by AccountDao referencedOn JournalEntries.account
    var value by JournalEntries.value
}
