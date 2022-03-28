package us.plp.bankaccount.infrastructure

import us.plp.bankaccount.usecases.Today

val todayFactory: () -> Today = fun (): Today {
    return fun (): String {
        TODO("Not yet implemented")
    }
}
