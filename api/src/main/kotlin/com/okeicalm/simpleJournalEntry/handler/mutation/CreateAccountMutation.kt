package com.okeicalm.simpleJournalEntry.handler.mutation

import com.expediagroup.graphql.server.operations.Mutation
import com.okeicalm.simpleJournalEntry.handler.type.AccountType
import com.okeicalm.simpleJournalEntry.usecase.account.AccountCreationUseCase
import org.springframework.stereotype.Component

data class CreateAccountInput(val code: String, val name: String, val elementType: Int)

@Component
class CreateAccountMutation(private val accountCreationUseCase: AccountCreationUseCase) : Mutation {
    fun createAccount(input: CreateAccountInput): AccountType {
        val account = accountCreationUseCase.call(
            code = input.code,
            name = input.name,
            elementType = input.elementType,
        )
        return AccountType(account)
    }
}
