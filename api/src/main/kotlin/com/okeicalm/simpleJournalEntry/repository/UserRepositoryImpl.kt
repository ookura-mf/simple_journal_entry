package com.okeicalm.simpleJournalEntry.repository

import com.okeicalm.simpleJournalEntry.entity.user.User
import com.okeicalm.simpleJournalEntry.entity.user.UserId
import com.okeicalm.simpleJournalEntry.tables.Users.*
import com.okeicalm.simpleJournalEntry.tables.pojos.Users
import org.jooq.DSLContext

import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl(private val dslContext: DSLContext): UserRepository {

    override fun findById(id: UserId): User? {
        val result = dslContext
            .fetchOne(USERS, USERS.ID.eq(id.value))
            ?.into(Users::class.java) // これ、ここで例外とか吐いたらどうなるんだろ

        return result?.let { User(it) }
    }

    override fun create(user: User): UserId {
        dslContext.newRecord(USERS, user).store()
        return UserId(dslContext.lastID().toLong())
    }

    override fun update(user: User): UserId {
        dslContext
            .update(USERS)
            .set(USERS.NAME, user.name.value)
            .where(USERS.ID.eq(user.id.value))
            .execute()

        return UserId(dslContext.lastID().toLong())
    }

    override fun delete(userId: UserId): UserId {
        dslContext
            .delete(USERS)
            .where(USERS.ID.eq(userId.value))
            .execute()
        return userId
    }
}