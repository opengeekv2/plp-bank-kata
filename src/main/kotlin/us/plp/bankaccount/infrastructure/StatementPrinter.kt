package us.plp.bankaccount.infrastructure

import us.plp.bankaccount.entities.Transactions
import us.plp.bankaccount.usecases.StatementPrinter

typealias Console = (String) -> Unit

fun printStatementFactory(println: Console): StatementPrinter {

    return fun (transactions: Transactions) {
        println("DATE       | AMOUNT  | BALANCE")
        transactions.forEach {
            println("${it.date}       | ${it.amount}  | ${it.balance}")
        }
    }
}
