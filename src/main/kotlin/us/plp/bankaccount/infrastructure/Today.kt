package us.plp.bankaccount.infrastructure

import us.plp.bankaccount.usecases.Today
import java.time.Clock
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun todayFactory(clock: Clock = Clock.systemDefaultZone()): Today {
    return fun (): String {
        return LocalDate.now(clock).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
    }
}
