package com.okeicalm.simpleJournalEntry.handler.type

import com.expediagroup.graphql.generator.annotations.GraphQLName
import com.expediagroup.graphql.generator.scalars.ID
import com.okeicalm.simpleJournalEntry.entity.user.User

private const val userTypeGraphQLName = "User"

@GraphQLName(userTypeGraphQLName)
data class UserType (
    val id: ID,
    val name: String
) {
    constructor(user: User) : this(
        ID(user.id.value.toString()),
        user.name.value
    )


}