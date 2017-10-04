package rj.vaadinKotlinCoop.view.error_handler;

import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.ErrorEvent;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import rj.vaadinKotlinCoop.view.i18n.I18nHelper;

import java.util.Random;

public class ErrorHandler extends DefaultErrorHandler {

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = -5475940025693631329L;

    private I18nHelper i18nHelper = new I18nHelper(I18nHelper.ResourceBundleName.MAIN_VIEW);

    @Override
    public void error(ErrorEvent event) {
        Window window = setupWindow(event.getThrowable());
        UI.getCurrent().addWindow(window);
    }

    private Window setupWindow(Throwable exception) {
        Window window = new Window("", getWindowContent(getCause(exception)));
        window.setHeight("600px");
        window.setWidth("700px");
        window.center();
        return window;
    }

    /**
     * Traverses stack trace to show you real reason why bad things happened
     *
     */
    private Throwable getCause(Throwable exception) {
        Throwable cause = exception;
        while (cause.getCause() != null) {
            cause = cause.getCause();
        }
        return cause;
    }

    private Component getWindowContent(Throwable exception) {
        return new Label(i18nHelper.get(
                        "errorMessage",
                // adding ?random=randomInt to image url prevents browser from caching it
                "" + new Random().nextInt()), ContentMode.HTML);
    }
}
