package us.plp.bankaccount.infrastructure

import us.plp.bankaccount.entities.Console

val println: Console = fun (string: String) {
    kotlin.io.println(string)
}