package com.okeicalm.simpleJournalEntry.usecase.account

import com.okeicalm.simpleJournalEntry.entity.Account
import com.okeicalm.simpleJournalEntry.repository.AccountRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

data class DeleteAccountUseCaseInput(val id: Long)
data class DeleteAccountUseCaseOutput(val account: Account?)

interface DeleteAccountUseCase {
    fun call(input: DeleteAccountUseCaseInput): DeleteAccountUseCaseOutput
}

@Service
class DeleteAccountUseCaseImpl(private val accountRepository: AccountRepository) : DeleteAccountUseCase {
    @Transactional
    override fun call(input: DeleteAccountUseCaseInput): DeleteAccountUseCaseOutput {
        val deletedAccount = accountRepository.findById(input.id) ?: return DeleteAccountUseCaseOutput(null)
        accountRepository.delete(input.id)
        return DeleteAccountUseCaseOutput(deletedAccount)
    }
}
