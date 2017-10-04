package rj.vaadinKotlinCoop.view

import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.spring.annotation.SpringView
import com.vaadin.spring.annotation.UIScope
import com.vaadin.ui.Button
import com.vaadin.ui.UI
import com.vaadin.ui.VerticalLayout
import rj.vaadinKotlinCoop.view.components.BalancesGrid
import rj.vaadinKotlinCoop.view.i18n.I18nHelper
import javax.inject.Inject

/**
 * Just the test view. Will be removed
 */
@UIScope
@SpringView(name = GridView.VIEW_NAME)
class GridView @Inject constructor(val balancesGrid: BalancesGrid) : VerticalLayout(), View {

    val i18nHelper = I18nHelper(I18nHelper.ResourceBundleName.BALANCES)

    override fun enter(event: ViewChangeListener.ViewChangeEvent?) {
        removeAllComponents()

        val backButton = Button(i18nHelper["back_label"]) { _ ->
            UI.getCurrent().navigator.navigateTo(MenuView.VIEW_NAME)
        }
        addComponent(backButton)
        addComponent(balancesGrid)
    }

    companion object {
        const val VIEW_NAME = "gridView"
    }
}