package rj.vaadinKotlinCoop

import com.vaadin.annotations.PreserveOnRefresh
import com.vaadin.annotations.Title
import com.vaadin.server.VaadinRequest
import com.vaadin.spring.annotation.SpringUI
import com.vaadin.spring.navigator.SpringNavigator
import com.vaadin.ui.UI
import rj.vaadinKotlinCoop.entity.Account
import rj.vaadinKotlinCoop.entity.Balance
import rj.vaadinKotlinCoop.service.AccountService
import rj.vaadinKotlinCoop.service.BalanceService
import rj.vaadinKotlinCoop.view.MainView
import java.math.BigDecimal
import java.util.*
import javax.inject.Inject

/**
 * Main UI of our application
 */
@PreserveOnRefresh
@Title("Vaadin Kotlin Cooperation example")
@SpringUI
class VaadinKotlinUI : UI() {

    // We'll use them to fill some random data at application startup
    @Inject lateinit var balanceService: BalanceService
    @Inject lateinit var accountService: AccountService

    @Inject lateinit var springNavigator: SpringNavigator

    override fun init(request: VaadinRequest?) {
        content = MainView(springNavigator)

        // make sure DB is clean - we don't have to do it again
        if (accountService.getAccountsCount() == 0L && balanceService.getBalancesCount() == 0L) {
            val addedAccounts = mutableListOf<Account>()

            // let's create random accounts
            (1..100).map {
                // accounts in accounting usually have numbers 100 XXX - it is not mandatory, but it is quite common
                "100 ${"$it".padStart(3, '0')}" // we can execute code within String using ${code goes here} syntax
            }.forEach {
                addedAccounts.add(
                        accountService.saveAccount(Account(number = it, name = "Account number $it")))
            }

            // Random is thread-safe, but in this case it doesn't matter at all, as it is used only to create some example values
            val r = Random()

            // Kotlin does not provide parallel streams (yet) - coroutines
            // provide similar functionality, but in version 1.1 they are still marked as "experimental"
            // We can use Java's streams though
            addedAccounts.parallelStream().forEach { account ->
                // now we'll create some data with random values
                balanceService.saveBalances(
                    (1..12).map {
                        Balance(
                            account = account,
                            debit = BigDecimal(r.nextDouble() * r.nextInt(1000)),
                            credit = BigDecimal(r.nextDouble() * r.nextInt(1000)),
                            period = it, //it representes current value from (1..12) stream
                            incrementalDebit = BigDecimal(r.nextDouble() * r.nextInt(1000)),
                            incrementalCredit = BigDecimal(r.nextDouble() * r.nextInt(1000)),
                            valueV = BigDecimal(r.nextDouble() * r.nextInt(1000)),
                            valueX = BigDecimal(r.nextDouble() * r.nextInt(1000)),
                            valueY = BigDecimal(r.nextDouble() * r.nextInt(1000)),
                            valueZ = BigDecimal(r.nextDouble() * r.nextInt(1000)))
                    })
            }
        }
    }
}