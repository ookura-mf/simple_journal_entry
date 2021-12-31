package com.okeicalm.simpleJournalEntry.repository

import com.okeicalm.simpleJournalEntry.entity.Account

interface AccountRepository {
    fun findAll(): List<Account>
}
