
package com.okeicalm.simpleJournalEntry.repository

import com.okeicalm.simpleJournalEntry.entity.Article
import com.okeicalm.simpleJournalEntry.infra.db.tables.Articles
import com.okeicalm.simpleJournalEntry.infra.db.tables.references.ARTICLES
import org.jooq.DSLContext
import org.springframework.stereotype.Repository

interface ArticleRepository {
    fun findAll(): List<Article>
    fun findById(id: Long): Article?
    fun create(article: Article): Article
    fun update(article: Article): Article
    fun delete(id: Long): Long
}

@Repository
class ArticleRepositoryImpl(private val dslContext: DSLContext) : ArticleRepository {
    override fun findAll(): List<Article> {
        return dslContext.select()
            .from(Articles.ARTICLES)
            .fetch()
            .into(Article::class.java)
    }

    override fun findById(id: Long): Article? {
        return dslContext
            .fetchOne(Articles.ARTICLES, Articles.ARTICLES.ID.eq(id))
            ?.into(Article::class.java)
    }

    override fun create(article: Article): Article {
        val record = dslContext
            .newRecord(ARTICLES)
            .apply {
                title = article.title
                accountId = article.accountId
                content = article.content
                updatedAt = article.updatedAt
            }
        record.store()

        return article.copy(id = record.id!!)
    }

    override fun update(article: Article): Article {
        dslContext
            .update(Articles.ARTICLES)
            .set(Articles.ARTICLES.TITLE, article.title)
            .set(Articles.ARTICLES.UPDATED_AT, article.updatedAt)
            .set(Articles.ARTICLES.CONTENT, article.content)
            .where(Articles.ARTICLES.ID.eq(article.id))
            .execute()
        return article
    }

    override fun delete(id: Long): Long {
        dslContext
            .delete(Articles.ARTICLES)
            .where(Articles.ARTICLES.ID.eq(id))
            .execute()
        return id
    }
}
