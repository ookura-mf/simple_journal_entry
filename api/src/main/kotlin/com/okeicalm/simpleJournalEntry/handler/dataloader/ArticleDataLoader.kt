package com.okeicalm.simpleJournalEntry.handler.dataloader

import com.expediagroup.graphql.generator.scalars.ID
import com.expediagroup.graphql.server.execution.KotlinDataLoader
import com.okeicalm.simpleJournalEntry.handler.type.ArticleType
import com.okeicalm.simpleJournalEntry.repository.ArticleRepository
import org.dataloader.DataLoader
import org.dataloader.DataLoaderFactory
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture

@Component
class ArticleDataLoader(val repository: ArticleRepository) : KotlinDataLoader<ID, List<ArticleType>> {
    override val dataLoaderName = "ArticleDataLoader"

    override fun getDataLoader(): DataLoader<ID, List<ArticleType>> = DataLoaderFactory.newDataLoader { ids ->
        CompletableFuture.supplyAsync {
            ids.map { id ->
                repository.filterByAccountIds(id.toString().toLong()).map { ArticleType(it) }
            }
        }
    }
}
