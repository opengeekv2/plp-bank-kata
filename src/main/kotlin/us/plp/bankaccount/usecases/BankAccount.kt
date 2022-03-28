package us.plp.bankaccount.usecases

import us.plp.bankaccount.entities.Deposit
import us.plp.bankaccount.entities.Transactions
import us.plp.bankaccount.entities.Withdrawal

typealias StatementPrinter = (Transactions) -> Unit

typealias Today = () -> String

class BankAccount(
    val transactions: Transactions,
    val printStatement: StatementPrinter, val today: Today) : Account {

    override fun deposit(amount: Int) {
        transactions.add(Deposit(today(), amount));
    }

    override fun withdrawal(amount: Int) {
        transactions.add(Withdrawal(today(), amount))
    }

    override fun printStatement() {
        printStatement(transactions)
    }

}
