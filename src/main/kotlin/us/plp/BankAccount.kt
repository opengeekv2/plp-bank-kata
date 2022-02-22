package us.plp

typealias StatementPrinter = (List<Any>) -> Unit

class BankAccount(val printStatement: StatementPrinter) : Account {

    override fun deposit(amount: Int) {
        TODO("Not yet implemented")
    }

    override fun withdrawal(amount: Int) {
        TODO("Not yet implemented")
    }

    override fun printStatement() {
        printStatement(listOf())
    }

}
