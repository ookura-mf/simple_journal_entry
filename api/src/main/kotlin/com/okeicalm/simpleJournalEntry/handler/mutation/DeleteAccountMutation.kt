package com.okeicalm.simpleJournalEntry.handler.mutation

import com.expediagroup.graphql.generator.scalars.ID
import com.expediagroup.graphql.server.operations.Mutation
import com.okeicalm.simpleJournalEntry.handler.type.AccountType
import com.okeicalm.simpleJournalEntry.usecase.account.DeleteAccountUseCase
import com.okeicalm.simpleJournalEntry.usecase.account.DeleteAccountUseCaseInput
import org.springframework.stereotype.Component

data class DeleteAccountInput(val id: ID)

data class DeleteAccountPayload(val deletedAccount: AccountType?)

@Component
class DeleteAccountMutation(private val deleteAccountUseCase: DeleteAccountUseCase) : Mutation {
    fun deleteAccount(input: DeleteAccountInput): DeleteAccountPayload {
        val output = deleteAccountUseCase.call(DeleteAccountUseCaseInput(id = input.id.toString().toLong()))

        val deleteAccountType = if (output.account == null) {
            null
        } else {
            AccountType(output.account)
        }

        return DeleteAccountPayload(deleteAccountType)
    }
}
