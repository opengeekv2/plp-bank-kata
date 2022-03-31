package us.plp.bankaccount.infrastructure

fun consoleFactory(): Console {
    return fun (string: String) {
        println(string)
    }
}