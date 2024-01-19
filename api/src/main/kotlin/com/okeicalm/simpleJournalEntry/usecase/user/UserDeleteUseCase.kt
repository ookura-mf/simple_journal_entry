package com.okeicalm.simpleJournalEntry.usecase.user

import com.okeicalm.simpleJournalEntry.entity.User
import com.okeicalm.simpleJournalEntry.repository.UserRepository
import org.springframework.stereotype.Service

data class UserDeleteUseCaseInput(val id: Long)
data class UserDeleteUseCaseOutput(val user: User?)

interface UserDeleteUseCase {
    fun call(input: UserDeleteUseCaseInput): UserDeleteUseCaseOutput
}

@Service
class UserDeleteUseCaseImpl(private val userRepository: UserRepository): UserDeleteUseCase {
    override fun call(input: UserDeleteUseCaseInput): UserDeleteUseCaseOutput {
        val deletedUser = userRepository.findById(input.id) ?: return UserDeleteUseCaseOutput(null)
        userRepository.delete(input.id)
        return UserDeleteUseCaseOutput(deletedUser)
    }
}
