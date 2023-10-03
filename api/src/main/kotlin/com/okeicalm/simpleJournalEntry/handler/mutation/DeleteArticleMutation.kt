package com.okeicalm.simpleJournalEntry.handler.mutation

import com.expediagroup.graphql.generator.scalars.ID
import com.expediagroup.graphql.server.operations.Mutation
import com.okeicalm.simpleJournalEntry.handler.type.ArticleType
import com.okeicalm.simpleJournalEntry.usecase.article.ArticleDeleteUseCase
import com.okeicalm.simpleJournalEntry.usecase.article.ArticleDeleteUseCaseInput
import org.springframework.stereotype.Component

data class DeleteArticleInput(val id: ID)

data class DeleteArticlePayload(val deletedArticle: ArticleType?)

@Component
class DeleteArticleMutation(private val articleDeletionUseCase: ArticleDeleteUseCase) : Mutation {
    fun deleteArticle(input: DeleteArticleInput): DeleteArticlePayload {
        val output = articleDeletionUseCase.call(ArticleDeleteUseCaseInput(id = input.id.toString().toLong()))

        val deleteArticleType = if (output.article == null) {
            null
        } else {
            ArticleType(output.article)
        }

        return DeleteArticlePayload(deleteArticleType)
    }
}
