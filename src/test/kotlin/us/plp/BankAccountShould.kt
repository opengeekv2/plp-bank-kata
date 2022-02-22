package us.plp

import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.Test

class BankAccountShould {

    private val println = spyk<Console>()
    private val statementPrinter = spyk<StatementPrinter>()

    @Test
    fun `should print an empty statement for an account with no operations`() {
        val account: Account = BankAccount(statementPrinter)
        account.printStatement()

        verify { statementPrinter(println, listOf()) };
    }
}