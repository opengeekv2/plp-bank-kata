package us.plp.bankaccount.usecases

interface Account {
    fun deposit(amount: Int): Unit

    fun withdrawal(amount: Int): Unit

    fun printStatement(): Unit
}