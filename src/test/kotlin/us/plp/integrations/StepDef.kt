package us.plp.integrations
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.mockk.spyk
import io.mockk.verify
import us.plp.*
import us.plp.unit.PRINTER_HEADER
import us.plp.unit.SPACING

class StepDef {
    private fun compareWithOutSpacing(expected: String, actual: String): Boolean {
        return expected.replace(SPACING, "") == actual.replace(SPACING, "")
    }
    private val println = spyk<Console>()
    private var account: Account? = null
    private var todayFactory: () -> Today = us.plp.todayFactory

    @Given("the date is {string}")
    fun the_date_is(string: String) {
        todayFactory = fun (): Today {
            return fun (): String {
                return "2022-02-24"
            }
        }
    }

    @Given("I have an empty account")
    fun i_have_an_empty_account() {
        account = BankAccount(printStatementFactory(println), todayFactory())
    }

    @When("I do a deposit of {string}")
    fun i_do_a_deposit_of(amount: String) {
        // Write code here that turns the phrase above into concrete actions
        account?.deposit(amount.toInt())
    }

    @When("I print the account statement")
    fun i_print_the_account_statement() {
        account?.printStatement()
    }

    @Then("I see an empty account statement")
    fun i_see_an_empty_account_statement() {
        verify { println(PRINTER_HEADER) }
    }

    @Then("I see an account statement for {string} with a deposit of {string} and a balance of {string}")
    fun i_see_an_account_statement_for_with_a_deposit_of_and_a_balance_of(
        date: String,
        amount: String,
        balance: String
    ) {
        verify {
            println(PRINTER_HEADER)
            println(match { param -> compareWithOutSpacing(param, "$date | $amount | $balance")})
        }
    }

}