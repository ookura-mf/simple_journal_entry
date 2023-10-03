package com.okeicalm.simpleJournalEntry.entity

import java.time.LocalDate

data class Article(
    val id: Long = 0,
    val accountId: Long = 0,
    val title: String,
    val content: String,
    val createdAt: LocalDate,
    val updatedAt: LocalDate,
)
