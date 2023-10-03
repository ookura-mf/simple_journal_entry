package com.okeicalm.simpleJournalEntry.usecase.article

import com.okeicalm.simpleJournalEntry.entity.Article
import com.okeicalm.simpleJournalEntry.repository.ArticleRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

data class ArticleUpdateUseCaseInput(val id: Long, val title: String, val content: String)
data class ArticleUpdateUseCaseOutput(val account: Article)

interface ArticleUpdateUseCase {
    fun call(input: ArticleUpdateUseCaseInput): ArticleUpdateUseCaseOutput
}

@Service
class ArticleUpdateUseCaseImpl(private val accountRepository: ArticleRepository) : ArticleUpdateUseCase {
    @Transactional
    override fun call(input: ArticleUpdateUseCaseInput): ArticleUpdateUseCaseOutput {
        val account = Article(
            id = input.id,
            title = input.title,
            content = input.content,
            updatedAt = LocalDate.now(),
            createdAt = LocalDate.now(),
        )

        return ArticleUpdateUseCaseOutput(accountRepository.update(account))
    }
}
