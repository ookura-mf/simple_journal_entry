package com.okeicalm.simpleJournalEntry.handler.type

import com.expediagroup.graphql.generator.annotations.GraphQLName
import com.expediagroup.graphql.generator.scalars.ID
import com.okeicalm.simpleJournalEntry.entity.JournalEntry

const val journalEntryTypeGraphQLName = "JournalEntry"

@GraphQLName(journalEntryTypeGraphQLName)
data class JournalEntryType(
    val id: ID,
    val side: Int,
    val value: Int,
    val journalId: ID,
    val accountId: ID,
) {
    constructor(journalEntry: JournalEntry) : this(
        ID(journalEntry.id.toString()),
        journalEntry.side.toInt(),
        journalEntry.value,
        ID(journalEntry.journalId.toString()),
        ID(journalEntry.accountId.toString()),
    )
}
