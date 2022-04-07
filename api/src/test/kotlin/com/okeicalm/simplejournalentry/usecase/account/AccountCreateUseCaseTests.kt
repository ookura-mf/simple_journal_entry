package com.okeicalm.simpleJournalEntry.usecase.account

import com.okeicalm.simpleJournalEntry.entity.Account
import com.okeicalm.simpleJournalEntry.repository.AccountRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class AccountCreateUseCaseTests : DescribeSpec({
    lateinit var usecase: AccountCreateUseCase
    val repository = mockk<AccountRepository>()

    beforeEach {
        usecase = AccountCreateUseCaseImpl(repository)
    }

    describe("call") {
        val input = AccountCreateUseCaseInput("code", "name", 1)
        val account = Account(code = "code", name = "name", elementType = 1)

        every { repository.create(account) } returns account.copy(id = 1)
        every { repository.findById(1) } returns account.copy(id = 1)

        it("returns new account") {
            val output = usecase.call(input)
            output.account.id.shouldBe(1)
            output.account.code.shouldBe("code")
            output.account.name.shouldBe("name")
            output.account.elementType.shouldBe(1)
        }
    }
})
