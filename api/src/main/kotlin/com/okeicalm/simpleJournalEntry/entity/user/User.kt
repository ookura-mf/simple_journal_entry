
package com.okeicalm.simpleJournalEntry.entity.user

import com.okeicalm.simpleJournalEntry.tables.pojos.Users

data class User(
    val id:   UserId = UserId(0), // id generator 的な某を使った方がよい？？
    val name: UserName
) {
    // ここ、何らかの腐敗防止層的なモノとして切り出すべき?
    constructor(user: Users) : this(
        UserId(user.id),
        UserName(user.name)
    )
}
