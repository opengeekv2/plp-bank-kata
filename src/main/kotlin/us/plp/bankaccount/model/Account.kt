package us.plp.bankaccount.model

interface Account {
    fun deposit(amount: Int): Unit

    fun withdrawal(amount: Int): Unit

    fun printStatement(): Unit
}