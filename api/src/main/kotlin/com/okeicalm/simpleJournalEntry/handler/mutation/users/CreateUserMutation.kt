package com.okeicalm.simpleJournalEntry.handler.mutation.users

import com.expediagroup.graphql.server.operations.Mutation
import com.okeicalm.simpleJournalEntry.handler.type.UserType
import com.okeicalm.simpleJournalEntry.usecase.user.CreateUserUseCaseImpl
import com.okeicalm.simpleJournalEntry.usecase.user.UserCreateUseCaseInput
import org.springframework.stereotype.Component

@Component
class CreateUserMutation(private val createUserUseCase: CreateUserUseCaseImpl): Mutation {
    fun create(name: String): UserType {
        val output = createUserUseCase.create(UserCreateUseCaseInput(name))
        return UserType(output.user)
    }
}