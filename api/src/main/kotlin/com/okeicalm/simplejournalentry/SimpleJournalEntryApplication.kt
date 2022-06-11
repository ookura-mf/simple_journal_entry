package com.okeicalm.simpleJournalEntry

import com.okeicalm.simpleJournalEntry.handler.scalar.CustomSchemaGeneratorHooks
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableTransactionManagement
class SimpleJournalEntryApplication {
    @Bean
    fun customGraphQLSchemaGeneratorHooks() = CustomSchemaGeneratorHooks()
}

fun main(args: Array<String>) {
    runApplication<SimpleJournalEntryApplication>(*args)
}
