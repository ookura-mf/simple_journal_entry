package com.okeicalm.simpleJournalEntry.entity

data class Account(
    val id: Long = 0,
    val code: String,
    val name: String,
    val elementType: Int,
)
