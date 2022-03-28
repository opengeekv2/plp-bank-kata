package us.plp.infrastructure

import us.plp.StatementPrinter
import us.plp.TransactionRepository

typealias Console = (String) -> Unit

val printStatementFactory: (Console) -> StatementPrinter = fun (println: Console): StatementPrinter {

    return fun (transactions: TransactionRepository) {
        println("DATE       | AMOUNT  | BALANCE")
        var balance = 0
        transactions.forEach {
            balance = it.applyTransaction(balance)
            println("${it.date}       | ${it.amount}  | $balance")
        }
    }
}
