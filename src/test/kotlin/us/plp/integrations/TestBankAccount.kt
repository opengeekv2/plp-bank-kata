package us.plp.integrations

import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.Test
import us.plp.*
import us.plp.unit.PRINTER_HEADER
import us.plp.unit.SPACING

class TestBankAccount {

    private val println = spyk<Console>()

    private fun compareWithOutSpacing(expected: String, actual: String): Boolean {
        return expected.replace(SPACING, "") == actual.replace(SPACING, "")
    }

    @Test
    fun `should print an empty statement for an account with no operations`() {
        val account: Account = BankAccount(printStatementFactory(println), todayFactory())
        account.printStatement()

        verify { println(PRINTER_HEADER) }
    }

    @Test
    fun `should print a statement with a deposit after a deposit is done`() {

        val account: Account = BankAccount(printStatementFactory(println), fun (): String {
            return "2022-02-24"
        })

        account.deposit(10000)

        account.printStatement()

        verify {
            println(PRINTER_HEADER)
            println(match { param -> compareWithOutSpacing(param, "2022-02-24 | 10000 | 10000")})
        }
    }

    @Test
    fun `should print many statements with a deposit after many deposits are done`() {

        val account: Account = BankAccount(printStatementFactory(println), fun (): String {
            return "2022-02-24"
        })

        account.deposit(10000)
        account.deposit(10000)

        account.printStatement()

        verify {
            println(PRINTER_HEADER)
            println(match { param -> compareWithOutSpacing(param, "2022-02-24 | 10000 | 10000")})
            println(match { param -> compareWithOutSpacing(param, "2022-02-24 | 10000 | 20000")})
        }
    }

}