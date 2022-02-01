package com.okeicalm.simpleJournalEntry.usecase.user

import com.okeicalm.simpleJournalEntry.entity.user.UserId
import com.okeicalm.simpleJournalEntry.repository.users.UserRepository


class DeleteUserUseCase(
    private val userRepository: UserRepository
) {
    fun delete(userId: UserId): UserId = userRepository.delete(userId)
}