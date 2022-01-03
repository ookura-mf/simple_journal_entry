package com.okeicalm.simpleJournalEntry.usecase.account

import com.okeicalm.simpleJournalEntry.entity.Account
import com.okeicalm.simpleJournalEntry.repository.AccountRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccountCreationUseCase(private val accountRepository: AccountRepository) {
    @Transactional
    fun call(code: String, name: String, elementType: Int): Account {
        val account = Account(
            code = code,
            name = name,
            elementType = elementType
        )
        val newAccountId = accountRepository.create(account)
        println(newAccountId)
        return accountRepository.findById(newAccountId) ?: throw Exception("AccountCreationUseCase: Something wrong...")
    }
}
