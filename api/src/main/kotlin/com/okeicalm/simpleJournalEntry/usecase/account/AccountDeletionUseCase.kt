package com.okeicalm.simpleJournalEntry.usecase.account

import com.okeicalm.simpleJournalEntry.entity.Account
import com.okeicalm.simpleJournalEntry.repository.AccountRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccountDeletionUseCase(private val accountRepository: AccountRepository) {
    @Transactional
    fun call(id: Long): Account? {
        val deletedAccount = accountRepository.findById(id) ?: return null
        accountRepository.delete(id)
        return deletedAccount
    }
}
