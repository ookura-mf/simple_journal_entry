package com.okeicalm.simpleJournalEntry.handlers.queries

import com.expediagroup.graphql.server.operations.Query
import com.okeicalm.simpleJournalEntry.handlers.types.UserType
import org.springframework.stereotype.Component

@Component
class UserQuery() : Query {
    @Suppress("unused")
    fun newUsers(): List<UserType> {
        return listOf()
    }
}
