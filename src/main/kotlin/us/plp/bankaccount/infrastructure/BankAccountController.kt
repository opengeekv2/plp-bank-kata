package us.plp.bankaccount.infrastructure

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import us.plp.bankaccount.usecases.BankAccount

@RestController
class BankAccountController(private val bankAccount: BankAccount) {

    @GetMapping("/bankaccount")
    fun getStatement() {
        bankAccount.printStatement()
    }
}