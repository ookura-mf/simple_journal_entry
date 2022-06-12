package com.okeicalm.simpleJournalEntry.entity

import com.okeicalm.simpleJournalEntry.valueobject.AccountElementType

data class Account(
    val id: Long = 0,
    val code: String,
    val name: String,
    val elementType: AccountElementType,
)
