package us.plp.unit

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import us.plp.*
import us.plp.infrastructure.InMemoryTransactionRepository

class TransactionRepositoryShould {

    @Test
    fun `add a deposit`(
    ) {
        val deposit = Deposit("23/03/2022", 100)
        val transactionRepository = InMemoryTransactionRepository()
        transactionRepository.add(deposit)
        assertThat(transactionRepository.getAll()).isEqualTo(listOf(deposit))
    }

    @Test
    fun `add a withdrawal`(
    ) {
        val withdrawal = Withdrawal("24/03/2022", 200)
        val transactionRepository = InMemoryTransactionRepository()
        transactionRepository.add(withdrawal)
        assertThat(transactionRepository.getAll()).isEqualTo(listOf(withdrawal))
    }

    @Test
    fun `forEach runs forEach on all transactions`(
    ) {
        val deposit = Deposit("23/03/2022", 100)
        val withdrawal = Withdrawal("24/03/2022", 200)
        val transactionRepository = InMemoryTransactionRepository()
        transactionRepository.add(deposit)
        transactionRepository.add(withdrawal)
        transactionRepository.forEach {
            assertThat(it).isIn(listOf(deposit, withdrawal))
        }
    }
}