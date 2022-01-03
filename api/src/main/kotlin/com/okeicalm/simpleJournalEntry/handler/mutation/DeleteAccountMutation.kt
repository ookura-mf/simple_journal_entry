package com.okeicalm.simpleJournalEntry.handler.mutation

import com.expediagroup.graphql.generator.scalars.ID
import com.expediagroup.graphql.server.operations.Mutation
import com.okeicalm.simpleJournalEntry.handler.type.AccountType
import com.okeicalm.simpleJournalEntry.usecase.account.AccountDeletionUseCase
import org.springframework.stereotype.Component

data class DeleteAccountInput(val id: ID)

data class DeleteAccountPayload(val deletedAccount: AccountType?)

@Component
class DeleteAccountMutation(private val accountDeletionUseCase: AccountDeletionUseCase) : Mutation {
    fun deleteAccount(input: DeleteAccountInput): DeleteAccountPayload {
        val deleteAccount = accountDeletionUseCase.call(id = input.id.toString().toLong())

        val deleteAccountType = if (deleteAccount == null) {
            null
        } else {
            AccountType(deleteAccount)
        }

        return DeleteAccountPayload(deleteAccountType)
    }
}
