package us.plp

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test


class TestBankAccount {

    private val console = mockk<Console>()

    @Test
    fun `should print an empty statement for an account with no operations`() {
        val account: Account = BankAccount()
        account.printStatement()

        verify { console.println("DATE       | AMOUNT  | BALANCE") }
    }

}