package com.okeicalm.simpleJournalEntry.handler.query

import com.okeicalm.simpleJournalEntry.entity.user.User
import com.okeicalm.simpleJournalEntry.entity.user.UserId
import com.okeicalm.simpleJournalEntry.repository.users.UserRepository

class UserQuery(private val repository: UserRepository) {
    fun getUser(id: Long): User? {
        return repository.findById(UserId(id))
    }

    fun getAllUsers(): List<User> {
        return repository.findAll()
    }
}