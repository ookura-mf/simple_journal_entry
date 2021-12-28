package com.okeicalm.simpleJournalEntry.handlers.types

import com.expediagroup.graphql.generator.annotations.GraphQLName
import com.expediagroup.graphql.generator.scalars.ID
import com.okeicalm.simpleJournalEntry.entities.User

const val userTypeGraphQLName = "User"

@GraphQLName(userTypeGraphQLName)
data class UserType(
    val id: ID,
    val name: String,
) {
    constructor(user: User) : this(
        ID(user.id.toString()),
        user.name,
    )
}
