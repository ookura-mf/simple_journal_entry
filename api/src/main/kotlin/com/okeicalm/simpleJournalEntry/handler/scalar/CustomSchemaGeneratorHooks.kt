package com.okeicalm.simpleJournalEntry.handler.scalar

import com.expediagroup.graphql.generator.hooks.SchemaGeneratorHooks
import graphql.scalars.ExtendedScalars
import graphql.schema.*
import java.time.LocalDate
import java.time.ZonedDateTime
import kotlin.reflect.KClass
import kotlin.reflect.KType

class CustomSchemaGeneratorHooks : SchemaGeneratorHooks {
    override fun willGenerateGraphQLType(type: KType): GraphQLType? = when (type.classifier as? KClass<*>) {
        ZonedDateTime::class -> ExtendedScalars.DateTime
        LocalDate::class -> ExtendedScalars.Date
        else -> null
    }
}
