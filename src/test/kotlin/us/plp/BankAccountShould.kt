package us.plp

import io.mockk.justRun
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.Test

class BankAccountShould {

    @Test
    fun `should print an empty statement for an account with no operations`() {
        //Given
        val statementPrinter = mockk<StatementPrinter>()
        justRun { statementPrinter.printStatement(listOf()) }
        val account: Account = BankAccount(statementPrinter)

        //When
        account.printStatement()

        //Then
        verify { statementPrinter.printStatement(listOf()) }
    }
}