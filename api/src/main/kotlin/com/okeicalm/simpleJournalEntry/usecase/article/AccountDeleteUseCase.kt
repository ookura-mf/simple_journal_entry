package com.okeicalm.simpleJournalEntry.usecase.article

import com.okeicalm.simpleJournalEntry.entity.Article
import com.okeicalm.simpleJournalEntry.repository.ArticleRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

data class ArticleDeleteUseCaseInput(val id: Long)
data class ArticleDeleteUseCaseOutput(val article: Article?)

interface ArticleDeleteUseCase {
    fun call(input: ArticleDeleteUseCaseInput): ArticleDeleteUseCaseOutput
}

@Service
class ArticleDeleteUseCaseImpl(private val accountRepository: ArticleRepository) : ArticleDeleteUseCase {
    @Transactional
    override fun call(input: ArticleDeleteUseCaseInput): ArticleDeleteUseCaseOutput {
        val deletedArticle = accountRepository.findById(input.id) ?: return ArticleDeleteUseCaseOutput(null)
        accountRepository.delete(input.id)
        return ArticleDeleteUseCaseOutput(deletedArticle)
    }
}
