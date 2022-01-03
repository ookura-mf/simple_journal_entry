package com.okeicalm.simpleJournalEntry.handler.mutation

import com.expediagroup.graphql.generator.scalars.ID
import com.expediagroup.graphql.server.operations.Mutation
import com.okeicalm.simpleJournalEntry.handler.type.AccountType
import com.okeicalm.simpleJournalEntry.usecase.account.AccountUpdatingUseCase
import org.springframework.stereotype.Component

data class UpdateAccountInput(val id: ID, val code: String, val name: String, val elementType: Int)

@Component
class UpdateAccountMutation(private val accountUpdatingUseCase: AccountUpdatingUseCase) : Mutation {
    fun updateAccount(input: UpdateAccountInput): AccountType {
        val account = accountUpdatingUseCase.call(
            id = input.id.toString().toLong(),
            code = input.code,
            name = input.name,
            elementType = input.elementType,
        )
        return AccountType(account)
    }
}
