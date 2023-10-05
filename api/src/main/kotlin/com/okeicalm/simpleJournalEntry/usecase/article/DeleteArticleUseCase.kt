package com.okeicalm.simpleJournalEntry.usecase.article

import com.okeicalm.simpleJournalEntry.entity.Article
import com.okeicalm.simpleJournalEntry.repository.ArticleRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

data class DeleteArticleUseCaseInput(val id: Long)
data class DeleteArticleUseCaseOutput(val article: Article?)

interface DeleteArticleUseCase {
    fun call(input: DeleteArticleUseCaseInput): DeleteArticleUseCaseOutput
}

@Service
class DeleteArticleUseCaseImpl(private val accountRepository: ArticleRepository) : DeleteArticleUseCase {
    @Transactional
    override fun call(input: DeleteArticleUseCaseInput): DeleteArticleUseCaseOutput {
        val deletedArticle = accountRepository.findById(input.id) ?: return DeleteArticleUseCaseOutput(null)
        accountRepository.delete(input.id)
        return DeleteArticleUseCaseOutput(deletedArticle)
    }
}
