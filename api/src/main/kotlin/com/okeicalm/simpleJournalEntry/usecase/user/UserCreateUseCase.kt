package com.okeicalm.simpleJournalEntry.usecase.user

import com.okeicalm.simpleJournalEntry.entity.User
import com.okeicalm.simpleJournalEntry.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

data class  UserCreateUseCaseInput(val name: String)
data class UserCreateUseCaseOutput(val user: User)

interface UserCreateUseCase {
    fun call(input: UserCreateUseCaseInput): UserCreateUseCaseOutput
}

@Service
class UserCreateUseCaseImpl(private val userRepository: UserRepository): UserCreateUseCase {
    @Transactional
    override fun call(input: UserCreateUseCaseInput): UserCreateUseCaseOutput {
        val user = User(
            name = input.name
        )
        return UserCreateUseCaseOutput(userRepository.create(user))
    }
}
