package com.okeicalm.simpleJournalEntry.repository

import com.okeicalm.simpleJournalEntry.entity.user.*

// これはドメイン層に置かれる想定
interface UserRepository {
    fun findById(id: UserId): User

    fun create(user: User): UserId

    fun update(user: User): User

    fun delete(userId: UserId): UserId
}