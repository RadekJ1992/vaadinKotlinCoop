package rj.vaadinKotlinCoop.view.i18n

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.i18n.LocaleContextHolder
import java.io.Serializable
import java.text.MessageFormat
import java.util.*

/**
 * Helper for internationalization, providing easy access to selected resource bundle
 */
class I18nHelper : Serializable {

    val i18nBundle: ResourceBundle

    constructor(bundle: ResourceBundleName) {
        i18nBundle = ResourceBundle.getBundle("rj.vaadinKotlinCoop.${bundle.bundleName}", LocaleContextHolder.getLocale())
    }

    /**
     * Returns value assigned to key for current Locale accessed via
     * [org.springframework.context.i18n.LocaleContextHolder.getLocale].
     * If there is no value for provided key method returns key as a value, it does not throw any exception
     */
    operator fun get(key: String): String {
        try {
            return i18nBundle.getString(key)
        } catch (ex: MissingResourceException) {
            return key
        }
    }

    /**
     * Returns value formatted with provided parameters assigned to key for current Locale accessed via
     * [org.springframework.context.i18n.LocaleContextHolder.getLocale]. <br></br>
     * If there is no value for provided key method returns key as a value, it does not throw any exception

     * @param key provided i18n key
     * *
     * @return value assigned for key in resource bundle if it exists, formatted using provided parameters.
     * * If value does not exists provided key is returned
     */
    operator fun get(key: String, vararg properties: String?): String {
        try {
            val result = i18nBundle.getString(key)
            val format = MessageFormat(result)
            return format.format(properties)
        } catch (ex: MissingResourceException) {
            log.debug("Missing resource " + key + " in bundle " + i18nBundle.baseBundleName)
            return key
        }
    }

    // this is how we declare static fields in Kotlin
    companion object {
        val log: Logger = LoggerFactory.getLogger(I18nHelper::class.java)
    }

    enum class ResourceBundleName(val bundleName: String) {
        MAIN_VIEW("main_view"),
        BALANCES("balances"),
        FILE_UPLOAD("file_upload")
    }
}

