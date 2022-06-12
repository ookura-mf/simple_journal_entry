package com.okeicalm.simpleJournalEntry.handler.mutation

import com.expediagroup.graphql.generator.scalars.ID
import com.expediagroup.graphql.server.operations.Mutation
import com.okeicalm.simpleJournalEntry.handler.type.AccountType
import com.okeicalm.simpleJournalEntry.usecase.account.AccountUpdateUseCase
import com.okeicalm.simpleJournalEntry.usecase.account.AccountUpdateUseCaseInput
import com.okeicalm.simpleJournalEntry.valueobject.AccountElementType
import org.springframework.stereotype.Component

data class UpdateAccountInput(val id: ID, val code: String, val name: String, val elementType: AccountElementType)

@Component
class UpdateAccountMutation(private val accountUpdateUseCase: AccountUpdateUseCase) : Mutation {
    fun updateAccount(input: UpdateAccountInput): AccountType {
        val output = accountUpdateUseCase.call(
            AccountUpdateUseCaseInput(
                id = input.id.toString().toLong(),
                code = input.code,
                name = input.name,
                elementType = input.elementType,
            )
        )
        return AccountType(output.account)
    }
}
