package com.okeicalm.simpleJournalEntry.handler.type

import com.expediagroup.graphql.generator.annotations.GraphQLName
import com.expediagroup.graphql.generator.scalars.ID
import com.okeicalm.simpleJournalEntry.entity.Journal

const val journalTypeGraphQLName = "Journal"

@GraphQLName(journalTypeGraphQLName)
data class JournalType(
    val id: ID,
//    val incurredOn: LocalDate,
    val journalEntries: List<JournalEntryType>?,
) {
    constructor(journal: Journal) : this(
        ID(journal.id.toString()),
        journal.journalEntries?.map { JournalEntryType(it) }
    )
}
