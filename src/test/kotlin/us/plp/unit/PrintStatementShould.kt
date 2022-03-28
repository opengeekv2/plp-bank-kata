package us.plp.unit

import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.Test
import us.plp.Deposit
import us.plp.StatementPrinter
import us.plp.infrastructure.Console
import us.plp.infrastructure.InMemoryTransactionRepository
import us.plp.infrastructure.printStatementFactory

const val PRINTER_HEADER: String = "DATE       | AMOUNT  | BALANCE";
const val SPACING: String = " ";

class PrintStatementShould {

    private fun compareWithOutSpacing(expected: String, actual: String): Boolean {
        return expected.replace(SPACING, "") == actual.replace(SPACING, "")
    }

    @Test
    fun `should print an empty statement for an account with no operations`() {
        //Given
        val println = spyk<Console>()
        val printStatement: StatementPrinter = printStatementFactory(println)
        printStatement(InMemoryTransactionRepository())

        //Then
        verify { println(PRINTER_HEADER) }
    }

    @Test
    fun `should print an single statement for an account with some operations`() {
        //Given
        val println = spyk<Console>()
        val printStatement: StatementPrinter = printStatementFactory(println)
        val transactionRepository = InMemoryTransactionRepository()
        transactionRepository.add(Deposit("2022-02-24", 10000))
        printStatement(transactionRepository)

        //Then
        verify {
            println(PRINTER_HEADER)
            println(match { param -> compareWithOutSpacing(param, "2022-02-24 | 10000 | 10000")})
        }
    }
}