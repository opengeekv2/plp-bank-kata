package us.plp.bankaccount.usecases

interface Account {
    fun deposit(amount: Int)

    fun withdrawal(amount: Int)

    fun printStatement()
}