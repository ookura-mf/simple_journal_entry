package com.okeicalm.simpleJournalEntry.usecase.user

import com.okeicalm.simpleJournalEntry.entity.user.User
import com.okeicalm.simpleJournalEntry.entity.user.UserId
import com.okeicalm.simpleJournalEntry.repository.users.UserRepository

class UpdateUserUseCase(private val userRepository: UserRepository) {
    fun update(user: User): UserId = userRepository.update(user)
}