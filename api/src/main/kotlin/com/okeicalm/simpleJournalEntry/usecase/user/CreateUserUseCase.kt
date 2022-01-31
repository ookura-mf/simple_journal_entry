package com.okeicalm.simpleJournalEntry.usecase.user

import com.okeicalm.simpleJournalEntry.entity.user.User
import com.okeicalm.simpleJournalEntry.entity.user.UserName
import com.okeicalm.simpleJournalEntry.repository.users.UserRepository

data class UserCreateUseCaseInput(val name: String)
data class UserCreateUseCaseOutput(val user: User)

class CreateUserUseCase(
    private val userRepository: UserRepository
) {

    fun create(input: UserCreateUseCaseInput): UserCreateUseCaseOutput {
        val user = User(name = UserName(input.name))
        userRepository.create(user)
        return UserCreateUseCaseOutput(user)
    }
}