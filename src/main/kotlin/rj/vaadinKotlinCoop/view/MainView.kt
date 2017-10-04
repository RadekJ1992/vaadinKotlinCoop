package rj.vaadinKotlinCoop.view

import com.vaadin.spring.navigator.SpringNavigator
import com.vaadin.ui.*
import rj.vaadinKotlinCoop.view.i18n.I18nHelper

/**
 * This is the main view of the application
 */
class MainView constructor(val springNavigator: SpringNavigator) : VerticalLayout() {

    //represents container for all views
    private val viewHolder = MainViewHolder()

    // represents error view - it is displayed when spring navigator cannot resolve url
    private val errorView = ErrorView()

    private val i18nHelper = I18nHelper(I18nHelper.ResourceBundleName.MAIN_VIEW)

    init {
        removeAllComponents()

        val titleLabel = Label(i18nHelper["page_title"])

        addComponent(titleLabel)
        setComponentAlignment(titleLabel, Alignment.MIDDLE_CENTER)

        viewHolder.setSizeFull()
        addComponent(viewHolder)
        springNavigator.init(UI.getCurrent(), viewHolder)
        springNavigator.setErrorView(errorView)
        UI.getCurrent().navigator = springNavigator
    }
}