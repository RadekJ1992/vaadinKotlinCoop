package rj.vaadinKotlinCoop.view.components

import com.vaadin.data.provider.ListDataProvider
import com.vaadin.spring.annotation.SpringComponent
import com.vaadin.ui.Grid
import com.vaadin.ui.TextField
import com.vaadin.ui.components.grid.FooterRow
import org.springframework.context.annotation.Scope
import rj.vaadinKotlinCoop.entity.Balance
import rj.vaadinKotlinCoop.service.BalanceService
import rj.vaadinKotlinCoop.view.i18n.I18nHelper
import javax.inject.Inject

@SpringComponent
@Scope("prototype")
class BalancesGrid @Inject constructor(val balanceService: BalanceService) : Grid<Balance>() {

    val i18nHelper = I18nHelper(I18nHelper.ResourceBundleName.BALANCES)
    val provider = ListDataProvider<Balance>(mutableListOf())

    init {
        val totalRow: FooterRow = appendFooterRow()
        loadData(totalRow)
        generateHeaderRow()
        setSizeFull()
    }

    private fun loadData(totalRow: FooterRow) {
        provider.items.addAll(balanceService.getAllBalances())
        dataProvider = provider
        setColumns()
        setSelectionMode(SelectionMode.NONE)
        fillTotalRow(totalRow)
    }

    fun setColumns() {
        addColumn { balance -> balance.account?.number }.caption = i18nHelper["balance_grid.account_number"]
        addColumn { balance -> balance.account?.name }.caption = i18nHelper["balance_grid.account_name"]
        addColumn(Balance::period) { it.toString() }.caption = i18nHelper["balance_grid.period"]
        addColumn(Balance::credit) { it.toPlainString() }.caption = i18nHelper["balance_grid.credit"]
        addColumn(Balance::debit) { it.toPlainString() }.caption = i18nHelper["balance_grid.debit"]
        addColumn(Balance::incrementalCredit) { it.toPlainString() }.caption = i18nHelper["balance_grid.incremental_credit"]
        addColumn(Balance::incrementalDebit) { it.toPlainString() }.caption = i18nHelper["balance_grid.incremental_debit"]
        addColumn(Balance::valueV) { it.toPlainString() }.caption = i18nHelper["balance_grid.value_v"]
        addColumn(Balance::valueX) { it.toPlainString() }.caption = i18nHelper["balance_grid.value_x"]
        addColumn(Balance::valueY) { it.toPlainString() }.caption = i18nHelper["balance_grid.value_y"]
        addColumn(Balance::valueZ) { it.toPlainString() }.caption = i18nHelper["balance_grid.value_z"]

        frozenColumnCount = 2
    }

    private fun generateHeaderRow() {
        val filterRow = appendHeaderRow()
        val cell = filterRow.getCell(columns.first())
        // this is a "Account Number" column
        // in previous versions of Vaadin grid filters could be added in an easier way than in Vaadin 8

        if (cell != null) {
            cell.styleName = "filter-cell"
            // Have an input field to use for filter
            val filterField = TextField()
            filterField.setSizeFull()
            filterField.addValueChangeListener { change -> applyFilter(change.value) }
            cell.component = filterField
        }
    }

    private fun applyFilter(value: String?) {
        if (value.isNullOrBlank()) {
            provider.clearFilters()
        } else {
            provider.addFilter {
                t: Balance? -> t?.account?.number?.contains(regex = Regex(value ?: "")) ?: false
            }
        }
    }

    private fun fillTotalRow(totalRow: FooterRow) {
        val total = balanceService.getTotal(provider.items)
        setTotalFooterCell(totalRow)

        // in Vaadin 7 columns could be accessed by their identifier, in Vaadin 8 we have to use columns themselves
        setFooterCell(totalRow, columns[3], total.credit.toPlainString())
        setFooterCell(totalRow, columns[4], total.debit.toPlainString())
        setFooterCell(totalRow, columns[5], total.incrementalCredit.toPlainString())
        setFooterCell(totalRow, columns[6], total.incrementalDebit.toPlainString())
        setFooterCell(totalRow, columns[7], total.valueV.toPlainString())
        setFooterCell(totalRow, columns[8], total.valueX.toPlainString())
        setFooterCell(totalRow, columns[9], total.valueY.toPlainString())
        setFooterCell(totalRow, columns[10], total.valueZ.toPlainString())
    }

    private fun setTotalFooterCell(totalRow: FooterRow) {
        val accountNumber = totalRow.getCell(columns.first())
        accountNumber.text = "Total"
    }

    fun setFooterCell(totalRow: FooterRow, footerCell: Column<Balance, *>, text: String) {
        val cell = totalRow.getCell(footerCell)
        if (cell != null) {
            cell.text = text
        }
    }
}