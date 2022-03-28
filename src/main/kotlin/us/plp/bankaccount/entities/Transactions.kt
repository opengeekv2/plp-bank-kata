package us.plp.bankaccount.entities

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

    fun forEach(transactionConsumer: (StatementLine) -> Unit) {
        var balance = 0
        transactions.forEach {
            balance = it.applyTransaction(balance)
            transactionConsumer(StatementLine(it.date, it.amount, balance))
        }
    }
}