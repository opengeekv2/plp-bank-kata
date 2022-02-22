package us.plp

typealias Console = (String) -> Unit

class BankAccount(val statementPrinter: StatementPrinter) : Account {

    override fun deposit(amount: Int) {
        TODO("Not yet implemented")
    }

    override fun withdrawal(amount: Int) {
        TODO("Not yet implemented")
    }

    override fun printStatement() {
        statementPrinter.printStatement(listOf())
    }

}
