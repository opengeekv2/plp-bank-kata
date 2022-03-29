package us.plp.bankaccount.unit

import io.mockk.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import us.plp.bankaccount.entities.Deposit
import us.plp.bankaccount.entities.Transaction
import us.plp.bankaccount.entities.Transactions
import us.plp.bankaccount.entities.Withdrawal

class TransactionsShould {

    @Test
    fun `add a deposit`(
    ) {
        val deposit = Deposit("23/03/2022", 100)
        val mutableList = mockk<MutableList<Transaction>>()
        val transactionRepository = Transactions(mutableList)
        every { mutableList.add(deposit) } returns true

        transactionRepository.add(deposit)

        verify { mutableList.add(deposit) }
    }

    @Test
    fun `add a withdrawal`(
    ) {
        val withdrawal = Withdrawal("24/03/2022", 200)
        val mutableList = mockk<MutableList<Transaction>>()
        val transactionRepository = Transactions(mutableList)
        every { mutableList.add(withdrawal) } returns true

        transactionRepository.add(withdrawal)

        verify { mutableList.add(withdrawal) }
    }

    @Test
    fun `forEach runs forEach on all transactions`(
    ) {
        val deposit = Deposit("23/03/2022", 100)
        val withdrawal = Withdrawal("24/03/2022", 200)
        val transactionRepository = Transactions(mutableListOf(deposit, withdrawal))
        transactionRepository.forEach{
            assertThat(it.amount*it.amount).isIn(listOf(deposit.amount*deposit.amount, withdrawal.amount*withdrawal.amount))
            assertThat(it.date).isIn(listOf(deposit.date, withdrawal.date))
            assertThat(it.balance).isIn(listOf(100, -100))
        }
    }
}