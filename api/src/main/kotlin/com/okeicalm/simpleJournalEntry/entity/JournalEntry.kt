package com.okeicalm.simpleJournalEntry.entity

data class JournalEntry(
    val id: Long,
    val journalId: Long,
    val side: Byte,
    val accountId: Long,
    val value: Int,
) {
    companion object {
        fun create(
            side: Byte,
            accountId: Long,
            value: Int,
        ): JournalEntry {
            return JournalEntry(
                id = 0,
                journalId = 0,
                side = side,
                accountId = accountId,
                value = value,
            )
        }
    }
}
