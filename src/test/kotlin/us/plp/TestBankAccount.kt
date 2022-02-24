package us.plp

import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.Test

class TestBankAccount {

    private val println = spyk<Console>()

    @Test
    fun `should print an empty statement for an account with no operations`() {
        val account: Account = BankAccount(printStatementFactory(println))
        account.printStatement()

        verify { println("DATE       | AMOUNT  | BALANCE") }
    }

    @Test
    fun `should print a statement with a deposit after a deposit is done`() {
        val account: Account = BankAccount(printStatementFactory(println))

        account.deposit(10000)

        account.printStatement()

        verify {
            println("DATE       | AMOUNT  | BALANCE")
            println("2022-02-24       | 10000  | 10000")
        }
    }

}