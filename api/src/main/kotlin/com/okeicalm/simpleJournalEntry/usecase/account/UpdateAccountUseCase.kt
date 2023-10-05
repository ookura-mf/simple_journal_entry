package com.okeicalm.simpleJournalEntry.usecase.account

import com.okeicalm.simpleJournalEntry.entity.Account
import com.okeicalm.simpleJournalEntry.repository.AccountRepository
import com.okeicalm.simpleJournalEntry.valueobject.AccountElementType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

data class UpdateAccountUseCaseInput(val id: Long, val code: String, val name: String, val elementType: AccountElementType)
data class UpdateAccountUseCaseOutput(val account: Account)

interface UpdateAccountUseCase {
    fun call(input: UpdateAccountUseCaseInput): UpdateAccountUseCaseOutput
}

@Service
class UpdateAccountUseCaseImpl(private val accountRepository: AccountRepository) : UpdateAccountUseCase {
    @Transactional
    override fun call(input: UpdateAccountUseCaseInput): UpdateAccountUseCaseOutput {
        val account = Account(
            id = input.id,
            code = input.code,
            name = input.name,
            elementType = input.elementType
        )

        return UpdateAccountUseCaseOutput(accountRepository.update(account))
    }
}
