package rj.vaadinKotlinCoop

import com.vaadin.annotations.VaadinServletConfiguration
import com.vaadin.spring.server.SpringVaadinServlet
import org.springframework.stereotype.Component
import javax.servlet.annotation.WebServlet

@WebServlet(value = "/*", asyncSupported = true)
@VaadinServletConfiguration(productionMode = true, ui = VaadinKotlinUI::class)
@Component("vaadinServlet")
class VaadinKotlinServlet : SpringVaadinServlet()