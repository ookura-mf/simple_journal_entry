
package com.okeicalm.simpleJournalEntry.usecase.article

import com.okeicalm.simpleJournalEntry.entity.Article
import com.okeicalm.simpleJournalEntry.repository.ArticleRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

data class CreateArticleUseCaseInput(val accountId: Long, val title: String, val content: String)
data class CreateArticleUseCaseOutput(val article: Article)

interface CreateArticleUseCase {
    fun call(input: CreateArticleUseCaseInput): CreateArticleUseCaseOutput
}

@Service
class CreateArticleUseCaseImpl(private val articleRepository: ArticleRepository) : CreateArticleUseCase {
    @Transactional
    override fun call(input: CreateArticleUseCaseInput): CreateArticleUseCaseOutput {

        val account = Article(
            accountId = input.accountId,
            title = input.title,
            content = input.content,
            createdAt = LocalDate.now(),
            updatedAt = LocalDate.now(),
        )

        return CreateArticleUseCaseOutput(articleRepository.create(account))
    }
}
