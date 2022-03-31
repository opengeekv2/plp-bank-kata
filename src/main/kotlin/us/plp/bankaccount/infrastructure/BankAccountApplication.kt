package us.plp.bankaccount.infrastructure

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans
import us.plp.bankaccount.entities.Transactions
import us.plp.bankaccount.usecases.BankAccount
import us.plp.bankaccount.usecases.StatementPrinter
import us.plp.bankaccount.usecases.Today

fun beans() = beans {
    bean<Console>("println") {
        consoleFactory()
    }
    bean<StatementPrinter>("printStatement") {
        printStatementFactory(ref<Console>("println"))
    }
    bean<Today>("today") {
        todayFactory()
    }
    // THIS SHOULD BE REMOVED IF YOU DECLARE THEM WITH ANNOTATIONS
    bean<Transactions>()
    bean<BankAccount>()
}

class BeansInitializer : ApplicationContextInitializer<GenericApplicationContext> {

    override fun initialize(context: GenericApplicationContext) {
        beans().initialize(context)

    }

}

/*
YOU COULD USE THIS IF YOU WANT TO DECLARE BEANS ALSO WITH ANNOTATIONS
@Configuration
@ComponentScan(basePackages = ["us.plp.bankaccount"])
class AppConfig
*/

@SpringBootApplication
class BankAccountApplication

fun main(args: Array<String>) {
    runApplication<BankAccountApplication>(*args)
}