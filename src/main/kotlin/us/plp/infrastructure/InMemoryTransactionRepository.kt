package us.plp.infrastructure

import us.plp.Deposit
import us.plp.Transaction
import us.plp.TransactionRepository

class InMemoryTransactionRepository : TransactionRepository {

    private val transactions = mutableListOf<Transaction>()

    override fun add(transaction: Transaction) {
        transactions.add(transaction)
    }
    override fun getAll(): List<Transaction> {
        return transactions
    }
}