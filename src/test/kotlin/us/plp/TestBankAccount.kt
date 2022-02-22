package us.plp

import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.Test

class TestBankAccount {

    private val println = spyk<Console>()

    @Test
    fun `should print an empty statement for an account with no operations`() {
        val account: Account = BankAccount(StatementPrinter(println))
        account.printStatement()

        verify { println("DATE       | AMOUNT  | BALANCE") }
    }

}