package com.okeicalm.simpleJournalEntry.usecase.user

import com.okeicalm.simpleJournalEntry.entity.user.User
import com.okeicalm.simpleJournalEntry.entity.user.UserName
import com.okeicalm.simpleJournalEntry.repository.users.UserRepository
import org.springframework.stereotype.Component

data class UserCreateUseCaseInput(val name: String)
data class UserCreateUseCaseOutput(val user: User)

// Usecase もインタフェース定義して、その具象クラスを Interactor って言うらしい？正直 Impl で良い感。
// https://nrslib.com/clean-architecture-with-java/#outline__7_4
interface CreateUserUseCase {
    fun create(input: UserCreateUseCaseInput): UserCreateUseCaseOutput
}

@Component
class CreateUserUseCaseImpl(
    private val userRepository: UserRepository
) : CreateUserUseCase {

    override fun create(input: UserCreateUseCaseInput): UserCreateUseCaseOutput {
        val user = User(name = UserName(input.name))
        userRepository.create(user)
        return UserCreateUseCaseOutput(user)
    }
}