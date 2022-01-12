package com.okeicalm.simpleJournalEntry.entity

import com.okeicalm.simpleJournalEntry.tables.pojos.JournalEntries
import com.okeicalm.simpleJournalEntry.tables.pojos.Journals
import java.time.LocalDate

data class Journal(

    val id: Long = 0,
    val incurredOn: LocalDate,
    val journalEntries: List<JournalEntry>?
) {
    constructor(journal: Journals, journalEntries: List<JournalEntries>?) : this(
        journal.id,
        journal.incurredOn,
//        journalEntries?.map { JournalEntry(it) },
        null,
    )
}
