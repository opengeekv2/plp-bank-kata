package us.plp

import us.plp.bankaccount.model.Account

typealias StatementPrinter = (Transactions) -> Unit

typealias Today = () -> String

data class Deposit(override val date: String, override val amount: Int): Transaction(date, amount) {
    override fun applyTransaction(balance: Int): Int {
        return balance + amount
    }
}

data class Withdrawal(override val date: String, override val amount: Int): Transaction(date, amount) {
    override fun applyTransaction(balance: Int): Int {
        return balance - amount
    }
}

abstract class Transaction(open val date: String, open val amount: Int) {
    abstract fun applyTransaction(balance: Int): Int
}

class Transactions(val transactions: MutableList<Transaction> = mutableListOf()) {

    fun add(transaction: Transaction) {
        transactions.add(transaction)
    }

    fun forEach(transactionConsumer: (Transaction, Int) -> Unit) {
        var balance = 0
        transactions.forEach {
            balance = it.applyTransaction(balance)
            transactionConsumer(it, balance)
        }
    }
}

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
