package com.okeicalm.simpleJournalEntry.repository

import com.okeicalm.simpleJournalEntry.entity.Account
import com.okeicalm.simpleJournalEntry.tables.Accounts
import org.jooq.DSLContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class AccountJdbcRepository @Autowired constructor(private val dslContext: DSLContext) : AccountRepository {
    override fun findAll(): List<Account> {
        val result = dslContext.select()
            .from(Accounts.ACCOUNTS)
            .fetch()
            .into(com.okeicalm.simpleJournalEntry.tables.pojos.Accounts::class.java)

        return result.map { r -> Account(r) }
    }

    override fun findById(id: Long): Account? {
        val accountPOJO: com.okeicalm.simpleJournalEntry.tables.pojos.Accounts? = dslContext
            .fetchOne(Accounts.ACCOUNTS, Accounts.ACCOUNTS.ID.eq(id))
            ?.into(com.okeicalm.simpleJournalEntry.tables.pojos.Accounts::class.java)

        return accountPOJO?.let { Account(it) }
    }

    override fun create(account: Account): Long {
        dslContext
            .newRecord(Accounts.ACCOUNTS)
            .setName(account.name)
            .setCode(account.code)
            .setElementType(account.elementType)
            .store()
        return dslContext.lastID().toLong()
    }

    override fun update(id: Long, account: Account): Boolean {
        try {
            dslContext
                .update(Accounts.ACCOUNTS)
                .set(Accounts.ACCOUNTS.CODE, account.code)
                .set(Accounts.ACCOUNTS.NAME, account.name)
                .set(Accounts.ACCOUNTS.ELEMENT_TYPE, account.elementType)
                .where(Accounts.ACCOUNTS.ID.eq(id))
                .execute()
        } catch (e: Exception) { // TODO: fix it!!
            println(e)
            return false
        }
        return true
    }
}
