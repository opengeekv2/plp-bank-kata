package us.plp

typealias Console = (String) -> Unit

val printStatementFactory: (Console) -> StatementPrinter = fun (println: Console): StatementPrinter {
    return fun (list: List<Any>): Unit {
        println("DATE       | AMOUNT  | BALANCE")
    }
}
