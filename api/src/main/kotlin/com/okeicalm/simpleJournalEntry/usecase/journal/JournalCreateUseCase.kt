package com.okeicalm.simpleJournalEntry.usecase.journal

import com.okeicalm.simpleJournalEntry.entity.Journal
import com.okeicalm.simpleJournalEntry.entity.JournalEntry
import com.okeicalm.simpleJournalEntry.repository.JournalRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

data class JournalEntryInputData(val side: Byte, val accountID: Long, val value: Int)
data class JournalCreateUseCaseInput(val incurredOn: LocalDate, val journalEntryInputDatum: List<JournalEntryInputData>)
data class JournalCreateUseCaseOutput(val journal: Journal)

interface JournalCreateUseCase {
    fun call(input: JournalCreateUseCaseInput): JournalCreateUseCaseOutput
}

@Service
class JournalCreateUseCaseImpl(
    private val journalRepository: JournalRepository,
) : JournalCreateUseCase {
    @Transactional
    override fun call(input: JournalCreateUseCaseInput): JournalCreateUseCaseOutput {
        val journalEntries = input.journalEntryInputDatum.map {
            JournalEntry.create(
                accountId = it.accountID,
                side = it.side,
                value = it.value,
            )
        }
        val journal = Journal.create(
            incurredOn = input.incurredOn,
            journalEntries = journalEntries,
        )

        val createdJournal = journalRepository.create(journal)

        return JournalCreateUseCaseOutput(createdJournal)
    }
}
