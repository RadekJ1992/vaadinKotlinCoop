package rj.vaadinKotlinCoop.service

import org.springframework.stereotype.Service
import rj.vaadinKotlinCoop.entity.Account
import rj.vaadinKotlinCoop.persistence.AccountRepository
import javax.inject.Inject

@Service
class AccountService @Inject constructor(private val accountRepository: AccountRepository) {

    // one-liner methods can be written with just a '=' sign - we can skip the brackets
    fun saveAccount(account: Account) = accountRepository.saveAndFlush(account)

    fun getAllAccounts() = accountRepository.findAll()

    fun getAccountsCount() = accountRepository.count()
}