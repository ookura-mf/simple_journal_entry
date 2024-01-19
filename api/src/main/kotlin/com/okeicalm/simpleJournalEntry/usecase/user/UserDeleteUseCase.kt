package com.okeicalm.simpleJournalEntry.usecase.user

import com.okeicalm.simpleJournalEntry.repository.UserRepository
import org.springframework.stereotype.Service

data class UserDeleteUseCaseInput(val id: Long)
data class UserDeleteUseCaseOutput(val id: Long)

interface UserDeleteUseCase {
    fun call(input: UserDeleteUseCaseInput): UserDeleteUseCaseOutput
}

@Service
class UserDeleteUseCaseImpl(private val repository: UserRepository): UserDeleteUseCase {
    override fun call(input: UserDeleteUseCaseInput): UserDeleteUseCaseOutput {
        val id = input.id
        return UserDeleteUseCaseOutput(repository.delete(id))
    }

}
