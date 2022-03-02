package us.plp.integrations
import io.cucumber.java.en.Given
import io.cucumber.java.en.When
import io.cucumber.java.en.Then
import io.mockk.spyk
import io.mockk.verify
import us.plp.*
import us.plp.unit.PRINTER_HEADER

class StepDef {
    private val println = spyk<Console>()
    private var account: Account? = null

    @Given("I have an empty account")
    fun i_have_an_empty_account() {
        account = BankAccount(printStatementFactory(println), todayFactory())
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