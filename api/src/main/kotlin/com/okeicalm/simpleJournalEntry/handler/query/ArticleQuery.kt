package com.okeicalm.simpleJournalEntry.handler.query

import com.expediagroup.graphql.server.operations.Query
import com.okeicalm.simpleJournalEntry.handler.type.ArticleType
import com.okeicalm.simpleJournalEntry.repository.ArticleRepository
import org.springframework.stereotype.Component

@Component
class ArticleQuery(private val repository: ArticleRepository) : Query {
    fun allArticles(): List<ArticleType> {
        return repository.findAll().map { ArticleType(it) }
    }
}
