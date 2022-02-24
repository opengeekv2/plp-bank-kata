package us.plp

import io.mockk.spyk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

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

    @ParameterizedTest
    @CsvSource(
        "1",
        "10000",
    )
    fun `should place a deposit`(amountToDeposit: Int) {
        //Given
        val printStatement = spyk<StatementPrinter>()
        val account = BankAccount(printStatement)

        //When
        account.deposit(amountToDeposit)

        //Then
        assertThat(account.transaction).isEqualTo(amountToDeposit)
    }

    @Test
    fun `should print the first deposit after the deposit has been done`() {
        //Given
        val printStatement = spyk<StatementPrinter>()
        val account: Account = BankAccount(printStatement)

        //When
        account.deposit(10000)
        account.printStatement()

        //Then
        verify { printStatement(listOf(Deposit: (1)) }
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