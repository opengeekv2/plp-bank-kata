package us.plp.infrastructure

import us.plp.Transaction
import us.plp.TransactionRepository

class InMemoryTransactionRepository : TransactionRepository {
    override fun add(transaction: Transaction) {
        TODO("Yet to be implemented")
    }
    override fun getAll(): List<Transaction> {
        TODO("Yet to be implemented")
    }
}