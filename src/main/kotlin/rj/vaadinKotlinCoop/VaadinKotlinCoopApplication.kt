package rj.vaadinKotlinCoop

import com.vaadin.spring.annotation.EnableVaadinNavigation
import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@EnableVaadinNavigation
@SpringBootApplication
class VaadinKotlinCoopApplication {
    companion object {
        val log = LoggerFactory.getLogger(VaadinKotlinCoopApplication::class.java)
    }
}

fun main(args: Array<String>) {
    val application = SpringApplication(VaadinKotlinCoopApplication::class.java)
    val env = application.run(*args).environment
    VaadinKotlinCoopApplication.log.info("\n----------------------------------------------------------\n\t" +
            "Application '{}' is running! Access URLs:\n\t" +
            "Local: \t\thttp://127.0.0.1:{}" +
            "\n----------------------------------------------------------",
            env.getProperty("spring.application.name"),
            env.getProperty("server.port"))
}