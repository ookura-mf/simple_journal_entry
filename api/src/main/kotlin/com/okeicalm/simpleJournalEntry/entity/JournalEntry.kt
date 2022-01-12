package com.okeicalm.simpleJournalEntry.entity

import com.okeicalm.simpleJournalEntry.tables.pojos.JournalEntries

data class JournalEntry(
    val id: Long = 0,
    val journalId: Long,
    val side: Byte,
    val accountId: Long,
    val value: Int,
) {
    constructor(journalEntry: JournalEntries) : this(
        journalEntry.id,
        journalEntry.journalId,
        journalEntry.side,
        journalEntry.accountId,
        journalEntry.value,
    )
}
