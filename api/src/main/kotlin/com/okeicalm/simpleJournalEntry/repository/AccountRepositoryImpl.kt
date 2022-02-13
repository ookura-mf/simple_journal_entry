package com.okeicalm.simpleJournalEntry.repository

import com.okeicalm.simpleJournalEntry.entity.Account
import org.springframework.stereotype.Repository

@Repository
class AccountRepositoryImpl : AccountRepository {
    override fun findAll(): List<Account> {
        return listOf(Account(id = 0L, "hoge", "hoge", 1))
    }

    override fun findById(id: Long): Account? {
        return null
    }

    override fun create(account: Account): Long {
        return 1L
    }

    override fun update(id: Long, account: Account): Long {
        return 1L
    }

    override fun delete(id: Long): Long {
        return 1L
    }
}
