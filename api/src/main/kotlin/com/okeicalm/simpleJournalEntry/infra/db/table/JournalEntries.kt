package com.okeicalm.simpleJournalEntry.infra.db.table

import org.jetbrains.exposed.dao.id.LongIdTable

object JournalEntries : LongIdTable() {
    val journal = reference("journal_id", Journals)
    val side = byte("side")
    val account = reference("account_id", Accounts)
    val value = integer("value")
}
