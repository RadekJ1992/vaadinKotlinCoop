package rj.vaadinKotlinCoop.service

import org.springframework.stereotype.Service
import rj.vaadinKotlinCoop.entity.Balance
import rj.vaadinKotlinCoop.persistence.AccountRepository
import rj.vaadinKotlinCoop.persistence.BalanceRepository
import javax.inject.Inject

@Service
class BalanceService @Inject constructor(private val balanceRepository: BalanceRepository) {
    fun saveBalances(balances: Collection<Balance>) {
        balanceRepository.save(balances)
        balanceRepository.flush()
    }

    fun getAllBalances() = balanceRepository.findAll()

    fun getBalancesCount() = balanceRepository.count()
}