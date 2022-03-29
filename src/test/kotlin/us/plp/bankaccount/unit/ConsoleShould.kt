package us.plp.bankaccount.unit

import com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import us.plp.bankaccount.infrastructure.println

class ConsoleShould {

    @Test
    fun print() {
        val output = tapSystemOut {
            println("Test")
        }

        assertThat(output).isEqualTo("Test\n")
    }
}