package com.okeicalm.simpleJournalEntry.repository

import com.okeicalm.simpleJournalEntry.entity.Journal

interface JournalRepository {
    fun findAll(): List<Journal>

    fun findById(id: Long): Journal?

    fun create(journal: Journal): Long
}
