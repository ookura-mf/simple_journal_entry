package com.okeicalm.simpleJournalEntry.handler.mutation

import com.expediagroup.graphql.generator.scalars.ID
import com.expediagroup.graphql.server.operations.Mutation
import com.okeicalm.simpleJournalEntry.handler.type.UserType
import com.okeicalm.simpleJournalEntry.usecase.user.UserDeleteUseCase
import com.okeicalm.simpleJournalEntry.usecase.user.UserDeleteUseCaseInput
import org.springframework.stereotype.Component

data class DeleteUserInput(val id: ID)

data class DeleteUserPayload(val deletedUser: UserType?)

@Component
class DeleteUserMutation(private val userDeleteUseCase: UserDeleteUseCase): Mutation {
    fun deleteUser(input: DeleteUserInput): DeleteUserPayload {

        val output = userDeleteUseCase.call(UserDeleteUseCaseInput(input.id.toString().toLong()))

        val deletedUserType = if(output.user == null) {
            null
        } else {
            UserType(output.user)
        }

        return DeleteUserPayload(deletedUserType)
    }
}