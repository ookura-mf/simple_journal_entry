package com.okeicalm.simpleJournalEntry.usecase.account

import com.okeicalm.simpleJournalEntry.entity.Account
import com.okeicalm.simpleJournalEntry.repository.AccountRepository
import com.okeicalm.simpleJournalEntry.valueobject.AccountElementType
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class CreateArticleUseCaseTests : DescribeSpec({
    lateinit var usecase: CreateAccountUseCase
    val repository = mockk<AccountRepository>()

    beforeEach {
        usecase = CreateAccountUseCaseImpl(repository)
    }

    describe("call") {
        val input = CreateAccountUseCaseInput("code", "name", AccountElementType.ASSETS)
        val account = Account(code = "code", name = "name", elementType = AccountElementType.ASSETS)

        every { repository.create(account) } returns account.copy(id = 1)
        every { repository.findById(1) } returns account.copy(id = 1)

        it("returns new account") {
            val output = usecase.call(input)
            output.account.id.shouldBe(1)
            output.account.code.shouldBe("code")
            output.account.name.shouldBe("name")
            output.account.elementType.shouldBe(AccountElementType.ASSETS)
        }
    }
})
