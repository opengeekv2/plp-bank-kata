package us.plp.bankaccount.entities

data class Deposit(override val date: String, override val amount: Int): Transaction(date, amount) {

    override fun value(): Int {
        return amount
    }

}

data class Withdrawal(override val date: String, override val amount: Int): Transaction(date, amount) {

    override fun value(): Int {
        return -amount
    }
}

abstract class Transaction(open val date: String, open val amount: Int) {

    fun applyTransaction(balance: Int): Int {
        return balance + value()
    }

    abstract fun value(): Int
}

data class StatementLine(val date: String, val amount: Int, val balance: Int)

class Transactions(private val transactions: MutableList<Transaction> = mutableListOf()) {

    fun add(transaction: Transaction) {
        transactions.add(transaction)
    }

    fun forEach(transactionConsumer: (StatementLine) -> Unit) {
        var balance = 0
        transactions.forEach {
            balance = it.applyTransaction(balance)
            transactionConsumer(StatementLine(it.date, it.value(), balance))
        }
    }
}