package rj.vaadinKotlinCoop.view

import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.spring.annotation.SpringView
import com.vaadin.spring.annotation.UIScope
import com.vaadin.ui.*
import rj.vaadinKotlinCoop.view.i18n.I18nHelper

@UIScope
@SpringView(name = MenuView.VIEW_NAME)
class MenuView : VerticalLayout(), View {

    val i18nHelper = I18nHelper(I18nHelper.ResourceBundleName.MAIN_VIEW)

    override fun enter(event: ViewChangeListener.ViewChangeEvent?) {
        removeAllComponents()
        val gridButton = Button(i18nHelper["grid_label"]) { _ -> // _ is used to mark unused lambda parameters
            UI.getCurrent().navigator.navigateTo(GridView.VIEW_NAME)
        }
        addComponent(gridButton)

        val exceptionButton = Button(i18nHelper["exception_label"]) { _ ->
            throw RuntimeException("Ooops!")
        }
        addComponent(exceptionButton)

        val uploadButton = Button(i18nHelper["upload_label"]) { _ ->
            UI.getCurrent().navigator.navigateTo(UploadView.VIEW_NAME)
        }
        addComponent(uploadButton)
    }

    companion object {
        const val VIEW_NAME = "menuView"
    }
}