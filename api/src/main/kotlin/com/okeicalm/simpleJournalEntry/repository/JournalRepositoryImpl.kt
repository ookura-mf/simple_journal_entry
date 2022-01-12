package com.okeicalm.simpleJournalEntry.repository

import com.okeicalm.simpleJournalEntry.entity.Journal
import com.okeicalm.simpleJournalEntry.tables.JournalEntries.JOURNAL_ENTRIES
import com.okeicalm.simpleJournalEntry.tables.Journals.JOURNALS
import com.okeicalm.simpleJournalEntry.tables.pojos.Journals
import org.jooq.DSLContext
import org.springframework.stereotype.Repository

@Repository
class JournalRepositoryImpl(private val dslContext: DSLContext) : JournalRepository {
    override fun findAll(): List<Journal> {
        val result = dslContext
            .select()
            .from(JOURNALS)
            .join(JOURNAL_ENTRIES)
            .on(JOURNALS.ID.eq(JOURNAL_ENTRIES.JOURNAL_ID))
            .fetch()
            .into(Journals::class.java)
        return result.map { Journal(it, null) }
    }

    override fun findById(id: Long): Journal? {
        val journal = dslContext
            .fetchOne(JOURNALS, JOURNALS.ID.eq(id))
            ?.into(Journals::class.java)

        return journal?.let { Journal(it, emptyList()) }
    }

    override fun create(journal: Journal): Long {
        dslContext
            .newRecord(JOURNALS)
            .setIncurredOn(journal.incurredOn)
            .store()
        return dslContext.lastID().toLong()
    }
}
