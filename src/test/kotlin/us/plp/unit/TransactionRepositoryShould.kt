package us.plp.unit

import io.mockk.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import us.plp.*
import us.plp.infrastructure.InMemoryTransactionRepository

class TransactionRepositoryShould {

    @Test
    fun `add a deposit`(
    ) {
        val deposit = Deposit("23/03/2022", 100)
        val mutableList = mockk<MutableList<Transaction>>()
        val transactionRepository = InMemoryTransactionRepository(mutableList)
        every { mutableList.add(deposit) } returns true

        transactionRepository.add(deposit)

        verify { mutableList.add(deposit) }
    }

    @Test
    fun `add a withdrawal`(
    ) {
        val withdrawal = Withdrawal("24/03/2022", 200)
        val mutableList = mockk<MutableList<Transaction>>()
        val transactionRepository = InMemoryTransactionRepository(mutableList)
        every { mutableList.add(withdrawal) } returns true

        transactionRepository.add(withdrawal)

        verify { mutableList.add(withdrawal) }
    }

    @Test
    fun `forEach runs forEach on all transactions`(
    ) {
        val deposit = Deposit("23/03/2022", 100)
        val withdrawal = Withdrawal("24/03/2022", 200)
        val transactionRepository = InMemoryTransactionRepository(mutableListOf(deposit, withdrawal))
        transactionRepository.forEach {
            assertThat(it).isIn(listOf(deposit, withdrawal))
        }
    }
}