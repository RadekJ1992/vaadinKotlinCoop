package rj.vaadinKotlinCoop.service

import org.springframework.stereotype.Service
import rj.vaadinKotlinCoop.entity.Balance
import rj.vaadinKotlinCoop.persistence.AccountRepository
import rj.vaadinKotlinCoop.persistence.BalanceRepository
import java.math.BigDecimal
import javax.inject.Inject

@Service
class BalanceService @Inject constructor(private val balanceRepository: BalanceRepository) {
    fun saveBalances(balances: Collection<Balance>) {
        balanceRepository.save(balances)
        balanceRepository.flush()
    }

    fun getTotal(balances: Collection<Balance>) : Balance {
        return balances.reduce {
            // in Kotlin we can use destructuring declarations
            (_, _, debit1, credit1, _, incrementalDebit1, incrementalCredit1, valueV1, valueX1, valueY1, valueZ1),
            (_, _, debit2, credit2, _, incrementalDebit2, incrementalCredit2, valueV2, valueX2, valueY2, valueZ2) -> Balance(
                id = -1,
                account = null,
                period = 0,
                credit = credit1.add(credit2),
                debit = debit1.add(debit2),
                incrementalCredit = incrementalCredit1.add(incrementalCredit2),
                incrementalDebit = incrementalDebit1.add(incrementalDebit2),
                valueV = valueV1.add(valueV2),
                valueX = valueX1.add(valueX2),
                valueY = valueY1.add(valueY2),
                valueZ = valueZ1.add(valueZ2))
        }
    }

    fun getAllBalances() = balanceRepository.findAll()

    fun getBalancesCount() = balanceRepository.count()
}