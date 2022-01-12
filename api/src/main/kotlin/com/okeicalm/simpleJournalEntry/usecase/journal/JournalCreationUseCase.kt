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
data class JournalCreationInputData(val incurredOn: LocalDate, val journalEntryInputDatum: List<JournalEntryInputData>)
data class JournalCreationOutputData(val journal: Journal, val journalEntries: List<JournalEntry>)

data class SearchParams(override val journalId: Long, override val journalIds: List<Long>?) : JournalEntrySearchParams

interface IJournalCreationUseCase {
    fun call(input: JournalCreationInputData): JournalCreationOutputData
}

@Service
class JournalCreationUseCase(
    private val journalRepository: JournalRepository,
    private val journalEntryRepository: JournalEntryRepository
) : IJournalCreationUseCase {
    @Transactional
    override fun call(input: JournalCreationInputData): JournalCreationOutputData {
        val journal = Journal(
            incurredOn = input.incurredOn,
            journalEntries = emptyList(),
        )

        val newJournalId = journalRepository.create(journal)
        val newJournal =
            journalRepository.findById(newJournalId) ?: throw Exception("JournalCreationUseCase: Something wrong...")

        val journalEntries = input.journalEntryInputDatum.map {
            JournalEntry(
                journalId = newJournalId,
                side = it.side,
                accountId = it.accountID,
                value = it.value,
            )
        }
        journalEntryRepository.bulkCreate(journalEntries)
        val newJournalEntries = journalEntryRepository.search(SearchParams(newJournalId, null))

        return JournalCreationOutputData(newJournal, newJournalEntries)
    }
}
