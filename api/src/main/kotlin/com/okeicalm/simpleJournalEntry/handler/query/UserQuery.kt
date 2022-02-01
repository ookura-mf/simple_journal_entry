package com.okeicalm.simpleJournalEntry.handler.query

import com.okeicalm.simpleJournalEntry.entity.user.User
import com.okeicalm.simpleJournalEntry.entity.user.UserId
import com.okeicalm.simpleJournalEntry.handler.type.UserType
import com.okeicalm.simpleJournalEntry.repository.users.UserRepository

class UserQuery(private val repository: UserRepository) {
    fun getUser(id: Long, user: User?): UserType? {
        return repository.findById(UserId(id))?.let { UserType(it) }
    }

    fun getAllUsers(): List<UserType> {
        val users = repository.findAll()
        return users.map { UserType(it) }
    }
}