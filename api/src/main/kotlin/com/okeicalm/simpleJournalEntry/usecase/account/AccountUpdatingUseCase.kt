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
        accountRepository.update(id, account)

        return accountRepository.findById(id) ?: throw Exception("AccountingUpdateUseCase: Something wrong...")
    }
}
