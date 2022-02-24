package us.plp

import io.mockk.justRun
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
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

    @Test
    fun `should place a deposit`() {
        //Given
        val printStatement = spyk<StatementPrinter>()
        val account = BankAccount(printStatement)

        //When
        account.deposit(1)

        //Then
        assertThat(account.transaction).isEqualTo(1)
    }

    fun `should throw an exception when the deposit is less than 1`() {
        //Given
        val printStatement = spyk<StatementPrinter>()
        val account: Account = BankAccount(printStatement)

        //When
        account.deposit(0)

        //Then
        verify { printStatement(listOf()) }
    }
}