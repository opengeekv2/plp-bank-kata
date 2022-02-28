package us.plp

typealias StatementPrinter = (List<Deposit>) -> Unit

data class Deposit(val date: String, val amount: Int)

open class BankAccount(val printStatement: StatementPrinter) : Account {

    var transaction : Int = 0

    override fun deposit(amount: Int) {
        transaction = amount;
    }

    override fun withdrawal(amount: Int) {
        TODO("Not yet implemented")
    }

    override fun printStatement() {
        if (transaction > 0) printStatement(listOf(Deposit("2022-02-24", transaction)))
        else printStatement(listOf())
    }

}
