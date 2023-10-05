package com.okeicalm.simpleJournalEntry.handler.mutation

import com.expediagroup.graphql.generator.scalars.ID
import com.expediagroup.graphql.server.operations.Mutation
import com.okeicalm.simpleJournalEntry.handler.type.AccountType
import com.okeicalm.simpleJournalEntry.usecase.account.UpdateAccountUseCase
import com.okeicalm.simpleJournalEntry.usecase.account.UpdateAccountUseCaseInput
import com.okeicalm.simpleJournalEntry.valueobject.AccountElementType
import org.springframework.stereotype.Component

data class UpdateAccountInput(val id: ID, val code: String, val name: String, val elementType: AccountElementType)

@Component
class UpdateAccountMutation(private val updateAccountUseCase: UpdateAccountUseCase) : Mutation {
    fun updateAccount(input: UpdateAccountInput): AccountType {
        val output = updateAccountUseCase.call(
            UpdateAccountUseCaseInput(
                id = input.id.toString().toLong(),
                code = input.code,
                name = input.name,
                elementType = input.elementType,
            )
        )
        return AccountType(output.account)
    }
}
