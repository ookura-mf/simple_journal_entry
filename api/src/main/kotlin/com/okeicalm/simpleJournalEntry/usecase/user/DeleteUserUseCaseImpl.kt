package com.okeicalm.simpleJournalEntry.usecase.user

import com.okeicalm.simpleJournalEntry.entity.user.UserId
import com.okeicalm.simpleJournalEntry.repository.users.UserRepository
import org.springframework.stereotype.Component

interface DeleteUserUseCase {
    fun delete(userId: UserId): UserId
}

@Component
class DeleteUserUseCaseImpl(
    private val userRepository: UserRepository
): DeleteUserUseCase {
    override fun delete(userId: UserId): UserId = userRepository.delete(userId)
}