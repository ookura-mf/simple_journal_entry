package com.okeicalm.simpleJournalEntry

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableTransactionManagement
class SimpleJournalEntryApplication

fun main(args: Array<String>) {
    runApplication<SimpleJournalEntryApplication>(*args)
}
