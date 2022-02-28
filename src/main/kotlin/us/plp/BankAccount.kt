package us.plp

typealias StatementPrinter = (List<Deposit>) -> Unit

typealias Today = () -> String

data class Deposit(val date: String, val amount: Int)

open class BankAccount(val printStatement: StatementPrinter, val today: Today) : Account {

    var transaction : MutableList<Deposit> = mutableListOf<Deposit>()

    override fun deposit(amount: Int) {
        transaction.add(Deposit(today(), amount));
    }

    override fun withdrawal(amount: Int) {
        TODO("Not yet implemented")
    }

    override fun printStatement() {
        printStatement(transaction)
    }

}
