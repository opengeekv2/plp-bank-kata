package us.plp

import us.plp.infrastructure.InMemoryTransactionRepository

typealias StatementPrinter = (TransactionRepository) -> Unit

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

interface TransactionRepository {
    fun add(transaction: Transaction)
    fun getAll(): List<Transaction>
    fun forEach(transactionConsumer: (Transaction) -> Unit)
}

class BankAccount(
    val transactionRepository: TransactionRepository,
    val printStatement: StatementPrinter, val today: Today) : Account {

    override fun deposit(amount: Int) {
        transactionRepository.add(Deposit(today(), amount));
    }

    override fun withdrawal(amount: Int) {
        transactionRepository.add(Withdrawal(today(), amount))
    }

    override fun printStatement() {
        printStatement(transactionRepository)
    }

}
