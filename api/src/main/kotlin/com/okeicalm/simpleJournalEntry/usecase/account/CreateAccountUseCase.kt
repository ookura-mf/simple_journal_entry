package com.okeicalm.simpleJournalEntry.usecase.account

import com.okeicalm.simpleJournalEntry.entity.Account
import com.okeicalm.simpleJournalEntry.repository.AccountRepository
import com.okeicalm.simpleJournalEntry.valueobject.AccountElementType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

data class CreateAccountUseCaseInput(val code: String, val name: String, val elementType: AccountElementType)
data class CreateAccountUseCaseOutput(val account: Account)

interface CreateAccountUseCase {
    fun call(input: CreateAccountUseCaseInput): CreateAccountUseCaseOutput
}

@Service
class CreateAccountUseCaseImpl(private val accountRepository: AccountRepository) : CreateAccountUseCase {
    @Transactional
    override fun call(input: CreateAccountUseCaseInput): CreateAccountUseCaseOutput {
        val account = Account(
            code = input.code,
            name = input.name,
            elementType = input.elementType
        )
        return CreateAccountUseCaseOutput(accountRepository.create(account))
    }
}
