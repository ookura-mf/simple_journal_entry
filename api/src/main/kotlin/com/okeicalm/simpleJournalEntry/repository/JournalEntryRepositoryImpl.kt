package com.okeicalm.simpleJournalEntry.repository

import com.okeicalm.simpleJournalEntry.entity.JournalEntry
import org.springframework.stereotype.Repository

@Repository
class JournalEntryRepositoryImpl : JournalEntryRepository {
    override fun search(params: JournalEntrySearchParams): List<JournalEntry> {
        return listOf(
            JournalEntry(
                1L,
                1L,
                1,
                1L,
                1,
            )
        )
    }

    override fun create(journalEntry: JournalEntry): Long {
        return 1L
    }

    override fun bulkCreate(journalEntries: List<JournalEntry>): Long {
        return 1L
    }
}
