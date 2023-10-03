package com.okeicalm.simpleJournalEntry.handler.mutation

import com.expediagroup.graphql.generator.scalars.ID
import com.expediagroup.graphql.server.operations.Mutation
import com.okeicalm.simpleJournalEntry.handler.type.ArticleType
import com.okeicalm.simpleJournalEntry.usecase.article.ArticleUpdateUseCase
import com.okeicalm.simpleJournalEntry.usecase.article.ArticleUpdateUseCaseInput
import org.springframework.stereotype.Component

data class UpdateArticleInput(val id: ID, val title: String, val content: String)

@Component
class UpdateArticleMutation(private val articleUpdateUseCase: ArticleUpdateUseCase) : Mutation {
    fun updateArticle(input: UpdateArticleInput): ArticleType {
        val output = articleUpdateUseCase.call(
            ArticleUpdateUseCaseInput(
                id = input.id.toString().toLong(),
                title = input.title,
                content = input.content,
            )
        )
        return ArticleType(output.account)
    }
}
