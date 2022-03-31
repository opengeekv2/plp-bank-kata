package us.plp.bankaccount.infrastructure

import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import us.plp.bankaccount.usecases.BankAccount

@Component
@RestController
class BankAccountController(private val bankAccount: BankAccount) {

    @GetMapping("/bankaccount")
    fun getStatement() {
        bankAccount.printStatement()
    }
}