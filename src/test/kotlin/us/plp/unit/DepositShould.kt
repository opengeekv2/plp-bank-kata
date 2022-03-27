package us.plp.unit

import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import us.plp.*

class DepositShould {

    @ParameterizedTest
    @CsvSource(
        "0, 100, 100",
        "100, 100, 200",
    )
    fun `be applied in a balance statement as a sum of the amount`(
        initialBalance: Int, amount: Int, finalBalance: Int
    ) {
        val deposit = Deposit("23/03/2022", amount)
        var balance = initialBalance
        balance = deposit.applyTransaction(balance)
        assertThat(balance).isEqualTo(finalBalance)
    }
}