package com.okeicalm.simpleJournalEntry.entity

import com.expediagroup.graphql.generator.scalars.ID
import com.okeicalm.simpleJournalEntry.tables.pojos.Accounts

data class Account(
    val id: Int = 0,
    val code: String,
    val name: String,
    val elementType: Int,
) {
    constructor(account: Accounts) : this(
        account.id,
        account.code,
        account.name,
        account.elementType,
    )
}
