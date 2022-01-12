package com.okeicalm.simpleJournalEntry.repository

import com.okeicalm.simpleJournalEntry.entity.JournalEntry
import com.okeicalm.simpleJournalEntry.tables.JournalEntries.JOURNAL_ENTRIES
import com.okeicalm.simpleJournalEntry.tables.pojos.JournalEntries
import org.jooq.DSLContext
import org.springframework.stereotype.Repository

@Repository
class JournalEntryRepositoryImpl(private val dslContext: DSLContext) : JournalEntryRepository {
    override fun search(params: JournalEntrySearchParams): List<JournalEntry> {
        val query = dslContext.select().from(JOURNAL_ENTRIES)
        if (params.journalId != null) {
            query.where(JOURNAL_ENTRIES.JOURNAL_ID.eq(params.journalId))
        }

        return query
            .fetch()
            .into(JournalEntries::class.java)
            .map { JournalEntry(it) }
    }

    override fun create(journalEntry: JournalEntry): Long {
        dslContext
            .newRecord(JOURNAL_ENTRIES)
            .setJournalId(journalEntry.journalId)
            .setSide(journalEntry.side)
            .setAccountId(journalEntry.accountId)
            .setValue(journalEntry.value)
            .store()
        return dslContext.lastID().toLong()
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
