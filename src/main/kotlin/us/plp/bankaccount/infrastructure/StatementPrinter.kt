package us.plp.bankaccount.infrastructure

import us.plp.StatementPrinter
import us.plp.Transaction
import us.plp.Transactions

typealias Console = (String) -> Unit

val printStatementFactory: (Console) -> StatementPrinter = fun (println: Console): StatementPrinter {

    return fun (transactions: Transactions) {
        println("DATE       | AMOUNT  | BALANCE")
        transactions.forEach(fun (transaction: Transaction, balance: Int) {
            println("${transaction.date}       | ${transaction.amount}  | $balance")
        })
    }
}
