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
}