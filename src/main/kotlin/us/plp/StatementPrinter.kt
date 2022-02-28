package us.plp

typealias Console = (String) -> Unit

val printStatementFactory: (Console) -> StatementPrinter = fun (println: Console): StatementPrinter {
    return fun (list: List<Deposit>): Unit {
        println("DATE       | AMOUNT  | BALANCE")
        for (item in list) {
            println("${item.date}       | ${item.amount}  | ${item.amount}")
        }
    }
}
