package us.plp.integrations
import io.cucumber.java.PendingException
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.mockk.spyk
import io.mockk.verify
import us.plp.*
import us.plp.unit.PRINTER_HEADER

class StepDef {
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
    fun i_do_a_deposit_of(string: String) {
        // Write code here that turns the phrase above into concrete actions
        account?.deposit(string.toInt())
    }

    @When("I print the account statement")
    fun i_print_the_account_statement() {
        account?.printStatement()
    }

    @Then("I see an empty account statement")
    fun i_see_an_empty_account_statement() {
        verify { println(PRINTER_HEADER) }
    }
}