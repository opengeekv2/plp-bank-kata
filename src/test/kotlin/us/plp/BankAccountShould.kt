package us.plp

import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class BankAccountShould {

    val printStatement = spyk<StatementPrinter>()
    val today = mockk<Today>()
    private var account: BankAccount = BankAccount(printStatement, today)

    @BeforeEach
    fun beforeEach() {
        account = BankAccount(printStatement, today)
    }

    @Test
    fun `should print an empty statement for an account with no operations`() {
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
        every { today() } returns "2022-02-24"

        //When
        account.deposit(amountToDeposit)

        //Then
        assertThat(account.transaction[0].amount).isEqualTo(amountToDeposit)
    }

    @ParameterizedTest
    @CsvSource(
        "2022-02-24, 10000",
        "2022-02-24, 1",
    )
    fun `should print the first deposit after the deposit has been done`(date: String, amountToDeposit: Int) {
        //Given
        every { today() } returns "2022-02-24"

        //When
        account.deposit(amountToDeposit)
        account.printStatement()

        //Then
        verify { printStatement(listOf(Deposit(date, amountToDeposit))) }
    }

    @Test
    fun `should print the correct date whenever a deposit is done`() {
        //Given
        every { today() } returns "2022-02-25"

        //When
        account.deposit(1)
        account.printStatement()

        //Then
        verify { printStatement(listOf(Deposit("2022-02-25", 1))) }
    }

    fun `should throw an exception when the deposit is less than 1`() {
        //Given
        every { today() } returns "2022-02-25"

        //When
        account.deposit(0)

        //Then
        verify { printStatement(listOf()) }
    }
}