package us.plp

typealias StatementPrinter = (List<Deposit>) -> Unit

typealias Today = () -> String

data class Deposit(override val date: String, override val amount: Int): Transaction(date, amount);
data class Withdrawal(override val date: String, override val amount: Int): Transaction(date, amount);

abstract class Transaction(open val date: String, open val amount: Int)

open class BankAccount(val printStatement: StatementPrinter, val today: Today) : Account {

    var transaction : MutableList<Transaction> = mutableListOf<Transaction>()

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
