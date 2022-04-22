package us.plp.bankaccount.infrastructure

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import us.plp.bankaccount.usecases.Account

@RestController
class BankAccountController(private val account: Account) {

    @GetMapping("/bankaccount")
    fun getStatement() {
        account.printStatement()
    }
}