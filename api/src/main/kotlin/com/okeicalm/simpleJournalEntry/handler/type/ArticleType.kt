package com.okeicalm.simpleJournalEntry.handler.type

import com.expediagroup.graphql.generator.annotations.GraphQLIgnore
import com.expediagroup.graphql.generator.annotations.GraphQLName
import com.expediagroup.graphql.generator.scalars.ID
import com.okeicalm.simpleJournalEntry.entity.Article
import java.time.LocalDate

const val articleTypeGraphQLName = "Article"

@GraphQLName(articleTypeGraphQLName)
data class ArticleType(
    val id: ID,
    val title: String,
    val content: String,
    val createdAt: LocalDate,
    val updatedAt: LocalDate,
    @GraphQLIgnore val accountId: ID,
) {
    constructor(article: Article) : this(
        ID(article.id.toString()),
        article.title,
        article.content,
        article.createdAt,
        article.updatedAt,
        ID(article.accountId.toString()),
    )
}
