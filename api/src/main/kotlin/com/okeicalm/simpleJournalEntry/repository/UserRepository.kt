package com.okeicalm.simpleJournalEntry.repository

import com.okeicalm.simpleJournalEntry.entity.Account
import com.okeicalm.simpleJournalEntry.entity.User
import com.okeicalm.simpleJournalEntry.infra.db.tables.references.USERS
import org.jooq.DSLContext
import org.springframework.stereotype.Repository

interface UserRepository {
    fun findAll(): List<User>
    fun create(user: User): User
    fun update(user: User): User
    fun delete(id: Long): Long
}

@Repository
class UserRepositoryImpl(private  val dslContext: DSLContext) : UserRepository {
    override fun findAll(): List<User> {
        return dslContext.select()
            .from(USERS)
            .fetch()
            .into(User::class.java)
    }

    override fun create(user: User): User {
        val record = dslContext
            .newRecord(USERS)
            .apply {
                name = user.name
            }
        record.store()

        return user.copy(id = record.id!!)
    }

    override fun update(user: User): User {
        dslContext
            .update(USERS)
            .set(USERS.NAME, user.name)
            .where(USERS.ID.eq(user.id))
            .execute()

        return user
    }

    override fun delete(id: Long): Long {
        dslContext
            .delete(USERS)
            .where(USERS.ID.eq(id))
            .execute()

        return id
    }
}
