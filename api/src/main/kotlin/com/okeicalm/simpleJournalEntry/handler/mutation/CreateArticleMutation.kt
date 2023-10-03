package com.okeicalm.simpleJournalEntry.handler.mutation

import com.expediagroup.graphql.server.operations.Mutation
import com.okeicalm.simpleJournalEntry.handler.type.ArticleType
import com.okeicalm.simpleJournalEntry.usecase.article.ArticleCreateUseCase
import com.okeicalm.simpleJournalEntry.usecase.article.ArticleCreateUseCaseInput
import org.springframework.stereotype.Component
import com.expediagroup.graphql.generator.scalars.ID

data class CreateArticleInput(val accountID: ID, val title: String, val content: String)

@Component
class CreateArticleMutation(private val articleCreateUseCase: ArticleCreateUseCase) : Mutation {
    fun createArticle(input: CreateArticleInput): ArticleType {
        val output = articleCreateUseCase.call(
            ArticleCreateUseCaseInput(
                accountId = input.accountID.toString().toLong(),
                title = input.title,
                content = input.content,
            )
        )
        return ArticleType(output.article)
    }
}
