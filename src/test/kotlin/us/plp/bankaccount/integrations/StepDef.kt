package us.plp.bankaccount.integrations
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import us.plp.*
import us.plp.bankaccount.infrastructure.Console
import us.plp.bankaccount.infrastructure.printStatementFactory
import us.plp.bankaccount.model.Account
import us.plp.bankaccount.unit.PRINTER_HEADER
import us.plp.bankaccount.unit.SPACING

class StepDef {
    private fun compareWithOutSpacing(expected: String, actual: String): Boolean {
        return expected.replace(SPACING, "") == actual.replace(SPACING, "")
    }
    private val println = spyk<Console>()
    private val today: Today = mockk<Today>()
    private var account: Account? = null

    @Given("the date is {string}")
    @When("the date becomes {string}")
    fun the_date_is(string: String) {
        every { today() } returns string
    }

    @Given("I have an empty account")
    fun i_have_an_empty_account() {
        account = BankAccount(
            Transactions(),
            printStatementFactory(println),
            today
        )
    }

    @When("I do a deposit of {string}")
    fun i_do_a_deposit_of(amount: String) {
        account!!.deposit(amount.toInt())
    }

    @When("I print the account statement")
    fun i_print_the_account_statement() {
        account!!.printStatement()
    }

    @When("I do a withdrawal of {string}")
    fun i_do_a_withdrawal_of(amount: String) {
        account!!.withdrawal(amount.toInt());
    }

    @Then("I see the header for the account statement")
    @Then("I see an empty account statement")
    fun i_see_an_empty_account_statement() {
        verify { println(PRINTER_HEADER) }
    }

    @Then("I see an account statement for {string} with a deposit of {string} and a balance of {string}")
    @Then("I see an account statement for {string} with a withdrawal of {string} and a balance of {string}")
    fun i_see_an_account_statement_for_with_a_deposit_of_and_a_balance_of(
        date: String,
        amount: String,
        balance: String
    ) {
        verify {
            println(match { param -> compareWithOutSpacing(param, "$date | $amount | $balance")})
        }
    }

}