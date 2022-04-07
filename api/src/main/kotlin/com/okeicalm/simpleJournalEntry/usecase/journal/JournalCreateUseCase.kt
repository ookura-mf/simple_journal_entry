package com.okeicalm.simpleJournalEntry.usecase.journal

import com.okeicalm.simpleJournalEntry.entity.Journal
import com.okeicalm.simpleJournalEntry.entity.JournalEntry
import com.okeicalm.simpleJournalEntry.repository.JournalEntryRepository
import com.okeicalm.simpleJournalEntry.repository.JournalEntrySearchParams
import com.okeicalm.simpleJournalEntry.repository.JournalRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

data class JournalEntryInputData(val side: Byte, val accountID: Long, val value: Int)
data class JournalCreateUseCaseInput(val incurredOn: LocalDate, val journalEntryInputDatum: List<JournalEntryInputData>)
data class JournalCreateUseCaseOutput(val journal: Journal, val journalEntries: List<JournalEntry>)

data class SearchParams(override val journalId: Long, override val journalIds: List<Long>?) : JournalEntrySearchParams

interface JournalCreateUseCase {
    fun call(input: JournalCreateUseCaseInput): JournalCreateUseCaseOutput
}

@Service
class JournalCreateUseCaseImpl(
    private val journalRepository: JournalRepository,
    private val journalEntryRepository: JournalEntryRepository
) : JournalCreateUseCase {
    @Transactional
    override fun call(input: JournalCreateUseCaseInput): JournalCreateUseCaseOutput {
        val journal = Journal(
            incurredOn = input.incurredOn,
            journalEntries = emptyList(),
        )

        val createdJournal = journalRepository.create(journal)

        val journalEntries = input.journalEntryInputDatum.map {
            JournalEntry(
                journalId = createdJournal.id,
                side = it.side,
                accountId = it.accountID,
                value = it.value,
            )
        }
        journalEntryRepository.bulkCreate(journalEntries)
        val createdJournalEntries = journalEntryRepository.search(SearchParams(createdJournal.id, null))

        return JournalCreateUseCaseOutput(createdJournal, createdJournalEntries)
    }
}
