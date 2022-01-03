package com.okeicalm.simpleJournalEntry.handler.type

import com.expediagroup.graphql.generator.annotations.GraphQLName
import com.expediagroup.graphql.generator.scalars.ID
import com.okeicalm.simpleJournalEntry.entity.Account

const val accountTypeGraphQLName = "Account"

@GraphQLName(accountTypeGraphQLName)
data class AccountType(
    val id: ID,
    val code: String,
    val name: String,
    val elementType: Int,
) {
    constructor(account: Account) : this(
        ID(account.id.toString()),
        account.code,
        account.name,
        account.elementType,
    )
}
