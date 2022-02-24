package us.plp

import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.Test

class PrintStatementShould {

    @Test
    fun `should print an empty statement for an account with no operations`() {
        //Given
        val println = spyk<Console>()
        val printStatement: StatementPrinter = printStatementFactory(println)
        printStatement(listOf())

        //Then
        verify { println("DATE       | AMOUNT  | BALANCE") }
    }

    @Test
    fun `should print an simgle statement for an account with some operations`() {
        //Given
        val println = spyk<Console>()
        val printStatement: StatementPrinter = printStatementFactory(println)
        printStatement(listOf(Deposit("2022-02-24", 10000)))

        //Then
        verify {
            println("DATE       | AMOUNT  | BALANCE")
            println("2022-02-24       | 10000  | 10000")
        }
    }
}