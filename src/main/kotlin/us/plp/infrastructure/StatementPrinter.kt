package us.plp.infrastructure

import us.plp.StatementPrinter
import us.plp.Transaction
import us.plp.TransactionRepository

typealias Console = (String) -> Unit

val printStatementFactory: (Console) -> StatementPrinter = fun (println: Console): StatementPrinter {

    return fun (transactions: TransactionRepository): Unit {
        println("DATE       | AMOUNT  | BALANCE")
        var balance = 0
        for (item in transactions.getAll()) {
            balance = item.applyTransaction(balance)
            println("${item.date}       | ${item.amount}  | $balance")
        }
    }
}
