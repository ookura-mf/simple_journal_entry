package com.okeicalm.simpleJournalEntry.repository

import com.okeicalm.simpleJournalEntry.entity.JournalEntry

interface JournalEntrySearchParams {
    val journalId: Long?
    val journalIds: List<Long>?
}

interface JournalEntryRepository {
    fun search(params: JournalEntrySearchParams): List<JournalEntry>

    fun create(journalEntry: JournalEntry): Long

    fun bulkCreate(journalEntries: List<JournalEntry>): Long
}
