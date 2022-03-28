package us.plp.infrastructure
import us.plp.Transaction
import us.plp.TransactionRepository

class InMemoryTransactionRepository(val transactions: MutableList<Transaction> = mutableListOf()) : TransactionRepository {

    override fun add(transaction: Transaction) {
        transactions.add(transaction)
    }

    override fun forEach(transactionConsumer: (Transaction) -> Unit) {
        transactions.forEach(transactionConsumer)
    }
}