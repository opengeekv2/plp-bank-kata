package us.plp

typealias Console = (String) -> Unit

val printStatementFactory: (Console) -> StatementPrinter = fun (println: Console): StatementPrinter {
    return fun (list: List<Deposit>): Unit {
        println("DATE       | AMOUNT  | BALANCE")
        var balance = 0
        for (item in list) {
            balance += item.amount;
            println("${item.date}       | ${item.amount}  | $balance")
        }
    }
}
