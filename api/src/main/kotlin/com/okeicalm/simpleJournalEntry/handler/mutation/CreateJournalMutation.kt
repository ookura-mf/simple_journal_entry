package com.okeicalm.simpleJournalEntry.handler.mutation

import com.expediagroup.graphql.generator.scalars.ID
import com.expediagroup.graphql.server.operations.Mutation
import com.okeicalm.simpleJournalEntry.handler.type.JournalEntryType
import com.okeicalm.simpleJournalEntry.handler.type.JournalType
import com.okeicalm.simpleJournalEntry.usecase.journal.IJournalCreationUseCase
import com.okeicalm.simpleJournalEntry.usecase.journal.JournalCreationInputData
import com.okeicalm.simpleJournalEntry.usecase.journal.JournalEntryInputData
import org.springframework.stereotype.Component
import java.time.LocalDate

data class CreateJournalInput(val incurredOn: Int, val createJournalEntryInput: List<CreateJournalEntryInput>)
data class CreateJournalEntryInput(val side: Int, val accountID: ID, val value: Int)

@Component
class CreateJournalMutation(private val journalCreationUseCase: IJournalCreationUseCase) : Mutation {
    fun createJournal(input: CreateJournalInput): JournalType {
        val journalEntryInputDatum = input.createJournalEntryInput.map {
            JournalEntryInputData(
                side = it.side.toByte(),
                accountID = it.accountID.toString().toLong(),
                value = it.value
            )
        }
        val inputData = JournalCreationInputData(
            incurredOn = LocalDate.now(), // TODO: dummy
            journalEntryInputDatum = journalEntryInputDatum
        )
        val outputData = journalCreationUseCase.call(inputData)

        return JournalType(
            id = ID(outputData.journal.id.toString()),
            journalEntries = outputData.journalEntries.map { JournalEntryType(it) }
        )
    }
}
