package com.okeicalm.simpleJournalEntry.entity

import java.time.LocalDate

data class Journal(
    val id: Long,
    val incurredOn: LocalDate,
    val journalEntries: List<JournalEntry>,
) {
    companion object {
        fun create(
            incurredOn: LocalDate,
            journalEntries: List<JournalEntry>,
        ): Journal {
            return Journal(
                id = 0,
                incurredOn = incurredOn,
                journalEntries = journalEntries,
            )
        }
    }
}
