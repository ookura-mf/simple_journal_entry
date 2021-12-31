package com.okeicalm.simpleJournalEntry.config

import org.jooq.codegen.DefaultGeneratorStrategy
import org.jooq.codegen.GeneratorStrategy
import org.jooq.meta.Definition

open class CustomGeneratorStrategy : DefaultGeneratorStrategy() {
    override fun getJavaClassName(definition: Definition?, mode: GeneratorStrategy.Mode?): String {
        val name = super.getJavaClassName(definition, mode)

        return when (mode) {
            GeneratorStrategy.Mode.POJO -> "${name}P"
            GeneratorStrategy.Mode.RECORD -> "${name}R"
            GeneratorStrategy.Mode.DEFAULT -> "${name}ByJ"
            else -> "${name}ByJ"
        }
    }
}
