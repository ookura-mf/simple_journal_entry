package com.okeicalm.simpleJournalEntry.usecase.account

import com.okeicalm.simpleJournalEntry.entity.Account
import com.okeicalm.simpleJournalEntry.repository.AccountRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

data class AccountDeleteUseCaseInput(val id: Long)
data class AccountDeleteUseCaseOutput(val account: Account?)

interface AccountDeleteUseCase {
    fun call(input: AccountDeleteUseCaseInput): AccountDeleteUseCaseOutput
}

@Service
class AccountDeleteUseCaseImpl(private val accountRepository: AccountRepository) : AccountDeleteUseCase {
    @Transactional
    override fun call(input: AccountDeleteUseCaseInput): AccountDeleteUseCaseOutput {
        val deletedAccount = accountRepository.findById(input.id) ?: return AccountDeleteUseCaseOutput(null)
        accountRepository.delete(input.id)
        return AccountDeleteUseCaseOutput(deletedAccount)
    }
}
