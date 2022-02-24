package us.plp

typealias StatementPrinter = (List<Any>) -> Unit

open class BankAccount(val printStatement: StatementPrinter) : Account {

    var transaction : Int = 0

    override fun deposit(amount: Int) {
        transaction = 1;
    }

    override fun withdrawal(amount: Int) {
        TODO("Not yet implemented")
    }

    override fun printStatement() {
        printStatement(listOf())
    }

}
