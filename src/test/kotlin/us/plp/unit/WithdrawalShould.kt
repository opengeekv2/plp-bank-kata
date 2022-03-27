package us.plp.unit

import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import us.plp.*

class WithdrawalShould {

    @ParameterizedTest
    @CsvSource(
        "200, 100, 100",
        "100, 100, 0",
    )
    fun `be applied in a balance statement as a substraction of the amount`(
        initialBalance: Int, amount: Int, finalBalance: Int
    ) {
        val withdrawal = Withdrawal("23/03/2022", amount)
        var balance = initialBalance
        balance = withdrawal.applyTransaction(balance)
        assertThat(balance).isEqualTo(finalBalance)
    }
}