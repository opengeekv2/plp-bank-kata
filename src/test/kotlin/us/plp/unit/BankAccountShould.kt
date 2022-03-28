package us.plp.unit

import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import us.plp.*

class BankAccountShould {

    private val transactionRepository = mockk<TransactionRepository>()
    private val printStatement = spyk<StatementPrinter>()
    private val today = mockk<Today>()
    private var account: BankAccount = BankAccount(transactionRepository, printStatement, today)

    @BeforeEach
    fun beforeEach() {
        account = BankAccount(transactionRepository, printStatement, today)
    }

    @Test
    fun `should print an empty statement for an account with no operations`() {
        //When
        account.printStatement()

        //Then
        verify { printStatement(transactionRepository) }
    }

    @ParameterizedTest
    @CsvSource(
        "1",
        "10000",
    )
    fun `should place a deposit`(amountToDeposit: Int) {
        //Given
        every { today() } returns "2022-02-24"
        every { transactionRepository.add(any()) } returns Unit

        //When
        account.deposit(amountToDeposit)

        //Then
        verify { transactionRepository.add(Deposit("2022-02-24", amountToDeposit)) }
    }

    @Test
    fun `should be able to withdrawal an amount from an account`() {
        //Given
        every { today() } returns "2022-02-25"
        every { transactionRepository.add(any()) } returns Unit

        //When
        account.withdrawal(1)

        //Then
        verify { transactionRepository.add(Withdrawal("2022-02-25", 1)) }
    }

    fun `should throw an exception when the deposit is less than 1`() {
        //Given
        every { today() } returns "2022-02-25"

        //When
        account.deposit(0)

        //Then
        verify { printStatement(transactionRepository) }
    }
}