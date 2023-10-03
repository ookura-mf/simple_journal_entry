
package com.okeicalm.simpleJournalEntry.usecase.article

import com.okeicalm.simpleJournalEntry.entity.Article
import com.okeicalm.simpleJournalEntry.repository.ArticleRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

data class ArticleCreateUseCaseInput(val accountId: Long, val title: String, val content: String)
data class ArticleCreateUseCaseOutput(val article: Article)

interface ArticleCreateUseCase {
    fun call(input: ArticleCreateUseCaseInput): ArticleCreateUseCaseOutput
}

@Service
class ArticleCreateUseCaseImpl(private val articleRepository: ArticleRepository) : ArticleCreateUseCase {
    @Transactional
    override fun call(input: ArticleCreateUseCaseInput): ArticleCreateUseCaseOutput {

        val account = Article(
            accountId = input.accountId,
            title = input.title,
            content = input.content,
            createdAt = LocalDate.now(),
            updatedAt = LocalDate.now(),
        )

        return ArticleCreateUseCaseOutput(articleRepository.create(account))
    }
}
