package com.okeicalm.simpleJournalEntry.handler.mutation

import com.expediagroup.graphql.generator.scalars.ID
import com.expediagroup.graphql.server.operations.Mutation
import com.okeicalm.simpleJournalEntry.usecase.article.DeleteArticleUseCase
import com.okeicalm.simpleJournalEntry.usecase.article.DeleteArticleUseCaseInput
import org.springframework.stereotype.Component

data class DeleteArticleInput(
    override val clientMutationId: String?,
    val articleId: ID,
) : MutationWithClientMutationId

data class DeleteArticlePayload(
    val clientMutationId: String?
)

@Component
class DeleteArticleMutation(private val deleteArticleUseCase: DeleteArticleUseCase) : Mutation {
    fun deleteArticle(input: DeleteArticleInput): DeleteArticlePayload {
        val output = deleteArticleUseCase.call(DeleteArticleUseCaseInput(id = input.articleId.toString().toLong()))

        return DeleteArticlePayload(clientMutationId = null)
    }
}
