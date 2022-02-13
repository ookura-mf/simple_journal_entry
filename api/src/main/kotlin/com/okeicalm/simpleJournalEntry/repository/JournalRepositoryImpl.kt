package com.okeicalm.simpleJournalEntry.repository

import com.okeicalm.simpleJournalEntry.entity.Journal
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
class JournalRepositoryImpl : JournalRepository {
    override fun findAll(): List<Journal> {
       return listOf(Journal(id = 0L, LocalDate.now(), null))
    }

    override fun findById(id: Long): Journal? {
        return null
    }

    override fun create(journal: Journal): Long {
        return 1L
    }
}
