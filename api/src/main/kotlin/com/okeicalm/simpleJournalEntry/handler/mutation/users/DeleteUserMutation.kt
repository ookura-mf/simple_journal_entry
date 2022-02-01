package com.okeicalm.simpleJournalEntry.handler.mutation.users

import com.expediagroup.graphql.generator.scalars.ID
import com.expediagroup.graphql.server.operations.Mutation
import com.okeicalm.simpleJournalEntry.entity.user.UserId
import com.okeicalm.simpleJournalEntry.repository.users.UserRepository
import org.springframework.stereotype.Component

@Component
class DeleteUserMutation(private val userRepository: UserRepository): Mutation {
    fun delete(id: Long) : ID {
        val deletedId = userRepository.delete(UserId(id))
        return ID(deletedId.value.toString()) // ここ、deletedId.toString() でもコンパイル通るの最高に危ないなぁ。テストで保証できれば良いかもだけど・
    }
}