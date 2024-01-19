package com.okeicalm.simpleJournalEntry.handler.mutation

import com.expediagroup.graphql.generator.scalars.ID
import com.expediagroup.graphql.server.operations.Mutation
import com.okeicalm.simpleJournalEntry.handler.type.UserType
import com.okeicalm.simpleJournalEntry.usecase.user.UserUpdateUseCase
import com.okeicalm.simpleJournalEntry.usecase.user.UserUpdateUseCaseInput
import org.springframework.stereotype.Component

data class UpdateUserInput(val id: ID, val name: String)

@Component
class UpdateUserMutation(private val userUpdateUseCase: UserUpdateUseCase): Mutation {
    fun updateUser(input: UpdateUserInput): UserType {
        val output = userUpdateUseCase.call(
            UserUpdateUseCaseInput(
                id = input.id.toString().toLong(),
                name = input.name
            )
        )
        return UserType(output.user)
    }
}
