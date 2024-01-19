package com.okeicalm.simpleJournalEntry.usecase.user

import com.okeicalm.simpleJournalEntry.entity.User
import com.okeicalm.simpleJournalEntry.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

data class UserUpdateUseCaseInput(val id: Long, val name: String)
data class UserUpdateUseCaseOutput(val user: User)

interface UserUpdateUseCase {
    fun call(input: UserUpdateUseCaseInput): UserUpdateUseCaseOutput
}

@Service
class UserUpdateUseCaseImpl(private val userRepository: UserRepository): UserUpdateUseCase {
    @Transactional
    override fun call(input: UserUpdateUseCaseInput): UserUpdateUseCaseOutput {
        val user = User(
            id = input.id,
            name = input.name
        )
        return UserUpdateUseCaseOutput(userRepository.update(user))
    }
}
