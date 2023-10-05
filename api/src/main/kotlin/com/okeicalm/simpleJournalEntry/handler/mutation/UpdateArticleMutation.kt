package com.okeicalm.simpleJournalEntry.handler.mutation

import com.expediagroup.graphql.generator.scalars.ID
import com.expediagroup.graphql.server.operations.Mutation
import com.okeicalm.simpleJournalEntry.handler.type.ArticleType
import com.okeicalm.simpleJournalEntry.usecase.article.UpdateArticleUseCase
import com.okeicalm.simpleJournalEntry.usecase.article.UpdateArticleUseCaseInput
import org.springframework.stereotype.Component

data class UpdateArticleInput(val id: ID, val title: String, val content: String)

@Component
class UpdateArticleMutation(private val updateArticleUseCase: UpdateArticleUseCase) : Mutation {
    fun updateArticle(input: UpdateArticleInput): ArticleType {
        val output = updateArticleUseCase.call(
            UpdateArticleUseCaseInput(
                id = input.id.toString().toLong(),
                title = input.title,
                content = input.content,
            )
        )
        return ArticleType(output.account)
    }
}
