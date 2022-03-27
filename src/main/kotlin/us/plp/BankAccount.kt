package us.plp

typealias StatementPrinter = (List<Transaction>) -> Unit

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

open class BankAccount(val printStatement: StatementPrinter, val today: Today) : Account {

    var transaction : MutableList<Transaction> = mutableListOf<Transaction>()

    override fun deposit(amount: Int) {
        transaction.add(Deposit(today(), amount));
    }

    override fun withdrawal(amount: Int) {
        transaction.add(Withdrawal(today(), amount))
    }

    override fun printStatement() {
        printStatement(transaction)
    }

}
