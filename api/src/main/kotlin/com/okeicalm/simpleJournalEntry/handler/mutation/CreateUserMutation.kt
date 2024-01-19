package com.okeicalm.simpleJournalEntry.handler.mutation

import com.expediagroup.graphql.server.operations.Mutation
import com.okeicalm.simpleJournalEntry.handler.type.UserType
import com.okeicalm.simpleJournalEntry.usecase.user.UserCreateUseCase
import com.okeicalm.simpleJournalEntry.usecase.user.UserCreateUseCaseInput
import org.springframework.stereotype.Component

data class CreateUserInput(val name: String)

@Component
class CreateUserMutation(private val userCreateUseCase: UserCreateUseCase): Mutation {
    fun createUser(input: CreateUserInput): UserType {
        val output = userCreateUseCase.call(
            UserCreateUseCaseInput(
                name = input.name
            )
        )
        return UserType(output.user)
    }
}
