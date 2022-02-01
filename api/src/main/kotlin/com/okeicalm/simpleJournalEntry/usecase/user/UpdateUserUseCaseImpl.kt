package com.okeicalm.simpleJournalEntry.usecase.user

import com.okeicalm.simpleJournalEntry.entity.user.User
import com.okeicalm.simpleJournalEntry.entity.user.UserName
import com.okeicalm.simpleJournalEntry.repository.users.UserRepository
import org.springframework.stereotype.Component

data class UserUpdateUseCaseInput(val name: String)
data class UserUpdateUseCaseOutput(val user: User)

interface UpdateUserUseCase {
    fun update(input: UserUpdateUseCaseInput): UserUpdateUseCaseOutput
}

@Component
class UpdateUserUseCaseImpl(
    private val userRepository: UserRepository
) : UpdateUserUseCase {
    override fun update(input: UserUpdateUseCaseInput): UserUpdateUseCaseOutput {
        val user = User(name = UserName(input.name))
        userRepository.update(User(name = UserName(input.name)))
        return UserUpdateUseCaseOutput(user)
    }
}