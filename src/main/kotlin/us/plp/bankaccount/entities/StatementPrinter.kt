package us.plp.bankaccount.entities

import us.plp.bankaccount.usecases.StatementPrinter

typealias Console = (String) -> Unit

data class StatementLine(val date: String, val amount: Int, val balance: Int)

val printStatementFactory: (Console) -> StatementPrinter = fun (println: Console): StatementPrinter {

    return fun (transactions: Transactions) {
        println("DATE       | AMOUNT  | BALANCE")
        transactions.forEach {
            println("${it.date}       | ${it.amount}  | ${it.balance}")
        }
    }
}
