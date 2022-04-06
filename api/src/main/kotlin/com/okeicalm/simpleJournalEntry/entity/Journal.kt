package com.okeicalm.simpleJournalEntry.entity

import java.time.LocalDate

data class Journal(
    val id: Long = 0,
    val incurredOn: LocalDate,
    val journalEntries: List<JournalEntry>?
)
