package com.okeicalm.simpleJournalEntry.handlers.types

import com.apollographql.federation.graphqljava._FieldSet.type
import com.expediagroup.graphql.generator.annotations.GraphQLName
import com.expediagroup.graphql.generator.scalars.ID
import com.okeicalm.simpleJournalEntry.entities.User
import graphql.relay.Relay

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