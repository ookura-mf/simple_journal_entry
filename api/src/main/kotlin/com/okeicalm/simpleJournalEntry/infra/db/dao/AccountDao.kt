package com.okeicalm.simpleJournalEntry.infra.db.dao

import com.okeicalm.simpleJournalEntry.infra.db.table.Accounts
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class AccountDao(id: EntityID<Long>): LongEntity(id) {
    companion object : LongEntityClass<AccountDao>(Accounts)
    var code by Accounts.code
    var name by Accounts.name
    var elementType by Accounts.elementType
}
