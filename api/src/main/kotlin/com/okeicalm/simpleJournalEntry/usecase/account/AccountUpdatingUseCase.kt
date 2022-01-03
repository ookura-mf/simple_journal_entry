package com.okeicalm.simpleJournalEntry.usecase.account

import com.okeicalm.simpleJournalEntry.entity.Account
import com.okeicalm.simpleJournalEntry.repository.AccountRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccountUpdatingUseCase(private val accountRepository: AccountRepository) {
    @Transactional
    fun call(id: Long, code: String, name: String, elementType: Int): Account {
        val account = Account(
            code = code,
            name = name,
            elementType = elementType
        )
        if (accountRepository.update(id, account)) {
            // Is it necessary?
            val updatedAccount = accountRepository.findById(id)

            // TODO: fix it.
            return updatedAccount!!
        } else {
            // TODO: fix it.
            throw Exception("Update error. Something wrong...")
        }
    }
}
