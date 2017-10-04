package rj.vaadinKotlinCoop.view

import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.spring.annotation.SpringView
import com.vaadin.spring.annotation.UIScope
import com.vaadin.ui.CssLayout
import com.vaadin.ui.Label

/**
 * Just the test view. Will be removed
 */
@UIScope
@SpringView(name = TestView.VIEW_NAME)
class TestView : CssLayout(), View {

    override fun enter(event: ViewChangeListener.ViewChangeEvent?) {
        removeAllComponents()
        addComponent(Label("test"))
    }

    companion object {
        const val VIEW_NAME = "testView"
    }
}