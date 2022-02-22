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
        val printStatement = spyk<StatementPrinter>()
        val account: Account = BankAccount(printStatement)

        //When
        account.printStatement()

        //Then
        verify { printStatement(listOf()) }
    }
}