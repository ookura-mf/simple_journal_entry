package com.okeicalm.simpleJournalEntry.repository

import com.okeicalm.simpleJournalEntry.entity.JournalEntry
import com.okeicalm.simpleJournalEntry.infra.db.tables.references.JOURNAL_ENTRIES
import org.jooq.DSLContext
import org.springframework.stereotype.Repository

interface JournalEntrySearchParams {
    val journalId: Long?
    val journalIds: List<Long>?
}

interface JournalEntryRepository {
    fun search(params: JournalEntrySearchParams): List<JournalEntry>
    fun create(journalEntry: JournalEntry): JournalEntry
    fun bulkCreate(journalEntries: List<JournalEntry>): Long
}

@Repository
class JournalEntryRepositoryImpl(private val dslContext: DSLContext) : JournalEntryRepository {
    override fun search(params: JournalEntrySearchParams): List<JournalEntry> {
        val query = dslContext.select().from(JOURNAL_ENTRIES)
        if (params.journalId != null) {
            query.where(JOURNAL_ENTRIES.JOURNAL_ID.eq(params.journalId))
        }

        return query
            .fetch()
            .into(JournalEntry::class.java)
    }

    override fun create(journalEntry: JournalEntry): JournalEntry {
        val record = dslContext
            .newRecord(JOURNAL_ENTRIES)
            .apply {
                journalId = journalEntry.journalId
                side = journalEntry.side
                accountId = journalEntry.accountId
                value = journalEntry.value
            }
        record.store()
        return journalEntry.copy(id = record.id!!)
    }

    override fun bulkCreate(journalEntries: List<JournalEntry>): Long {
        val queries = journalEntries.map {
            dslContext.insertInto(
                JOURNAL_ENTRIES,
                JOURNAL_ENTRIES.JOURNAL_ID,
                JOURNAL_ENTRIES.SIDE,
                JOURNAL_ENTRIES.ACCOUNT_ID,
                JOURNAL_ENTRIES.VALUE
            )
                .values(it.journalId, it.side, it.accountId, it.value)
        }
        dslContext.batch(queries).execute()

        return dslContext.lastID().toLong()
    }
}
