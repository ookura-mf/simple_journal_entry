package com.okeicalm.simpleJournalEntry.infra.db.table

import org.jetbrains.exposed.dao.id.LongIdTable

object Accounts : LongIdTable() {
    val code = varchar("code", length = 50)
    val name = varchar("name", length = 50)
    val elementType = integer("element_type")
}
