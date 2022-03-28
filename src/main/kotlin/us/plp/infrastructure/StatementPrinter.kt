package us.plp.infrastructure

import us.plp.StatementPrinter
import us.plp.Transaction
import us.plp.TransactionRepository

typealias Console = (String) -> Unit

val printStatementFactory: (Console) -> StatementPrinter = fun (println: Console): StatementPrinter {

    return fun (transactions: TransactionRepository) {
        println("DATE       | AMOUNT  | BALANCE")
        transactions.forEach(fun (transaction: Transaction, balance: Int) {
            println("${transaction.date}       | ${transaction.amount}  | $balance")
        })
    }
}
