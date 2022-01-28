package com.okeicalm.simpleJournalEntry.repository

import com.okeicalm.simpleJournalEntry.entity.user.User
import com.okeicalm.simpleJournalEntry.entity.user.UserId
import org.jooq.DSLContext

import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl(private val dslContext: DSLContext): UserRepository {

    override fun findById(id: UserId): User {
        TODO("Not yet implemented")
    }

    override fun create(user: User): UserId {
        TODO("Not yet implemented")
    }

    override fun update(user: User): User {
        TODO("Not yet implemented")
    }

    override fun delete(userId: UserId): UserId {
        TODO("Not yet implemented")
    }
}