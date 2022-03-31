package us.plp.bankaccount.unit

import io.mockk.*
import org.junit.jupiter.api.Test
import us.plp.bankaccount.entities.*
import us.plp.bankaccount.infrastructure.Console
import us.plp.bankaccount.infrastructure.printStatementFactory
import us.plp.bankaccount.usecases.StatementPrinter

const val PRINTER_HEADER: String = "DATE       | AMOUNT  | BALANCE"
const val SPACING: String = " "

class PrintStatementShould {

    private fun compareWithOutSpacing(expected: String, actual: String): Boolean {
        return expected.replace(SPACING, "") == actual.replace(SPACING, "")
    }

    @Test
    fun `should print an empty statement for an account with no operations`() {
        //Given
        val println = spyk<Console>()
        val printStatement: StatementPrinter = printStatementFactory(println)

        val transactionRepository = mockk<Transactions>(relaxed=true)

        printStatement(transactionRepository)

        //Then
        verify { println(PRINTER_HEADER) }
    }

    @Test
    fun `should print an single statement for an account with a deposit`() {
        //Given
        val println = spyk<Console>()
        val printStatement: StatementPrinter = printStatementFactory(println)
        val transactions = Transactions()

        transactions.add(Deposit("2022-02-24", 10000))

        printStatement(transactions)

        //Then
        verify {
            println(PRINTER_HEADER)
            println(match { param -> compareWithOutSpacing(param, "2022-02-24 | 10000 | 10000")})
        }
    }

    @Test
    fun `should print an two statement for an account with some a deposit and a withdrawal`() {
        //Given
        val println = spyk<Console>()
        val printStatement: StatementPrinter = printStatementFactory(println)
        val transactions = Transactions()

        transactions.add(Deposit("2022-02-24", 10000))
        transactions.add(Withdrawal("2022-02-24", 5000))

        printStatement(transactions)

        //Then
        verify {
            println(PRINTER_HEADER)
            println(match { param -> compareWithOutSpacing(param, "2022-02-24 | 10000 | 10000") } )
            println(match { param -> compareWithOutSpacing(param, "2022-02-24 | -5000 | 5000") } )
        }
    }
}