package com.okeicalm.simpleJournalEntry.fetcher

import com.okeicalm.simpleJournalEntry.entity.JournalEntry
import com.okeicalm.simpleJournalEntry.repository.JournalEntryRepository
import com.okeicalm.simpleJournalEntry.repository.JournalEntrySearchParams
import org.springframework.stereotype.Component

data class JournalEntrySearchUseCaseParams(val journalID: Long?, val journalIDs: List<Long>?) : JournalEntrySearchParams {
    override val journalId: Long? = null
    override val journalIds: List<Long>? = null
}

@Component
class JournalEntrySearchUseCase(private val journalEntryRepository: JournalEntryRepository) {
    fun call(params: JournalEntrySearchUseCaseParams): List<JournalEntry> {
        return journalEntryRepository.search(params)
    }
}
