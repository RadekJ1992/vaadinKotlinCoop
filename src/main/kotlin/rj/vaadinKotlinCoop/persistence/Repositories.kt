package rj.vaadinKotlinCoop.persistence

import org.springframework.data.jpa.repository.JpaRepository
import rj.vaadinKotlinCoop.entity.Account
import rj.vaadinKotlinCoop.entity.Balance
import rj.vaadinKotlinCoop.entity.UploadedFile

// In Kotlin we don't have to create separate file for each class/interface - we can create them in one file

// When there is no class/interface body we can skip the brackets {}
interface AccountRepository : JpaRepository<Account, Long>

interface BalanceRepository : JpaRepository<Balance, Long> {
    fun findByAccount(account: Account) : Set<Balance>
}

interface UploadedFileRepositody: JpaRepository<UploadedFile, Long>
