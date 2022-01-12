package com.okeicalm.simpleJournalEntry.handler.query

import com.expediagroup.graphql.server.operations.Query
import com.okeicalm.simpleJournalEntry.handler.type.JournalType
import com.okeicalm.simpleJournalEntry.repository.JournalRepository
import org.springframework.stereotype.Component

@Component
class JournalQuery(private val repository: JournalRepository) : Query {
    fun allJournals(): List<JournalType> {
        val journals = repository.findAll()
        return journals.map { JournalType(it) }
    }
}
