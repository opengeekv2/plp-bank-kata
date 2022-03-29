package us.plp.bankaccount.unit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import us.plp.bankaccount.infrastructure.todayFactory
import us.plp.bankaccount.usecases.Today
import java.time.Clock
import java.time.Instant
import java.time.ZoneId

class TodayShould {

    @Test
    fun `today should be today`() {
        val instant = Instant.parse("2020-01-01T00:00:00Z")
        val today = todayFactory(Clock.fixed(instant, ZoneId.of("UTC")))
        assertThat(today()).isEqualTo("01/01/2020")
    }

}