
package com.okeicalm.simpleJournalEntry.entity.user

import com.okeicalm.simpleJournalEntry.tables.pojos.Users

data class User(
    val id:   UserId = UserId(0),
    val name: UserName
) {
    // Enity に jOOQ の関心が漏れ出しているとも言える(が潔癖症すぎる考えかも)
    // ここ、Entity に書くべきではなく、何らかの腐敗防止層的な変換処理を挟むべき？？
    constructor(user: Users) : this(
        UserId(user.id),
        UserName(user.name)
    )
}
