package com.okeicalm.simpleJournalEntry.usecase.article

import com.okeicalm.simpleJournalEntry.entity.Article
import com.okeicalm.simpleJournalEntry.repository.ArticleRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

data class UpdateArticleUseCaseInput(val id: Long, val title: String, val content: String)
data class UpdateArticleUseCaseOutput(val account: Article)

interface UpdateArticleUseCase {
    fun call(input: UpdateArticleUseCaseInput): UpdateArticleUseCaseOutput
}

@Service
class UpdateArticleUseCaseImpl(private val accountRepository: ArticleRepository) : UpdateArticleUseCase {
    @Transactional
    override fun call(input: UpdateArticleUseCaseInput): UpdateArticleUseCaseOutput {
        val account = Article(
            id = input.id,
            title = input.title,
            content = input.content,
            updatedAt = LocalDate.now(),
            createdAt = LocalDate.now(),
        )

        return UpdateArticleUseCaseOutput(accountRepository.update(account))
    }
}
