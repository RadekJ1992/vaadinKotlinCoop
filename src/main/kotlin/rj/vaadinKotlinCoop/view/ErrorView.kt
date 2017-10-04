package rj.vaadinKotlinCoop.view

import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.spring.annotation.SpringView
import com.vaadin.ui.Component
import com.vaadin.ui.Label
import com.vaadin.ui.UI

/**
 * Error view required by Spring Navigator - it's content is shown when the navigator can't resolve given url
 */
@SpringView
class ErrorView : View {

    // this will not be displayed, but view requires a component
    override fun getViewComponent(): Component {
        return Label()
    }

    override fun enter(event: ViewChangeListener.ViewChangeEvent) {
        UI.getCurrent().navigator.navigateTo(MenuView.VIEW_NAME)
    }
}
