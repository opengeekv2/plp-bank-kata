package us.plp

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class BankAccountShould {

    private val statementPrinter = mockk<StatementPrinter>()

    @Test
    fun `should print an empty statement for an account with no operations`() {
        val account: Account = BankAccount()
        account.printStatement()

        verify { statementPrinter(listOf()) }
    }
}