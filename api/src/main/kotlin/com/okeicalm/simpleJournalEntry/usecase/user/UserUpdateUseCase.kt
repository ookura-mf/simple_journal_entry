package com.okeicalm.simpleJournalEntry.usecase.user

import com.okeicalm.simpleJournalEntry.entity.User
import com.okeicalm.simpleJournalEntry.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

data class userUpdateUseCaseInput(val name: String)
data class userUpdateUseCaseOutput(val user: User)

interface UserUpdateUseCase {
    fun call(input: userUpdateUseCaseInput): userUpdateUseCaseOutput
}

@Service
class UserUpdateUseCaseImpl(private val userRepository: UserRepository): UserUpdateUseCase {
    @Transactional
    override fun call(input: userUpdateUseCaseInput): userUpdateUseCaseOutput {
        val user = User(
            name = input.name
        )
        return userUpdateUseCaseOutput(userRepository.update(user))
    }
}