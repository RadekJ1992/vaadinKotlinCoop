package rj.vaadinKotlinCoop.view

import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.server.FileDownloader
import com.vaadin.server.StreamResource
import com.vaadin.spring.annotation.SpringView
import com.vaadin.spring.annotation.UIScope
import com.vaadin.ui.*
import rj.vaadinKotlinCoop.entity.UploadedFile
import rj.vaadinKotlinCoop.service.UploadedFileService
import rj.vaadinKotlinCoop.view.components.UploadComponent
import rj.vaadinKotlinCoop.view.i18n.I18nHelper
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.OutputStream
import javax.inject.Inject

/**
 * View with file uploader
 */
@UIScope
@SpringView(name = UploadView.VIEW_NAME)
class UploadView @Inject constructor(val uploadedFileService: UploadedFileService): VerticalLayout(), View {

    val i18nHelper = I18nHelper(I18nHelper.ResourceBundleName.FILE_UPLOAD)

    override fun enter(event: ViewChangeListener.ViewChangeEvent?) {
        refresh()
    }

    fun refresh() {
        removeAllComponents()

        val backButton = Button(i18nHelper["back_label"]) { _ ->
            UI.getCurrent().navigator.navigateTo(MenuView.VIEW_NAME)
        }
        addComponent(backButton)

        uploadedFileService.findAll().forEach {
            val layout = HorizontalLayout()
            layout.addComponent(Label(it.name))

            val downloadButton = Button(i18nHelper["download"])
            val fileResource = createResource(it.name ?: "", it.data ?: byteArrayOf())
            val fileDownloader = FileDownloader(fileResource)
            fileDownloader.extend(downloadButton)
            layout.addComponent(downloadButton)

            layout.setWidth("500px")
            addComponent(layout)
        }

        val form = FormLayout()

        val nameField = TextField(i18nHelper["uploaded_file_name"])
        form.addComponent(nameField)

        val receiver = FileUploader()
        receiver.nameField = nameField
        val upload = UploadComponent(i18nHelper["uploaded_file"], receiver)
        upload.addSucceededListener(receiver)
        form.addComponent(upload)

        val uploadPanel = Panel(i18nHelper["upload_file_label"], form)

        addComponent(uploadPanel)

    }

    /**
     * Creates resource for download after clicking "Download" button
     */
    private fun createResource(fileName: String, file: ByteArray): StreamResource {
        return StreamResource( { ByteArrayInputStream(file) }, fileName)
    }

    companion object {
        const val VIEW_NAME = "uploadView"
    }

    /**
     * Class used as and file upload handler and Vaadin Listener of successful file upload
     */
    private inner class FileUploader : Upload.Receiver, Upload.SucceededListener {

        var filename: String? = null
        var mimeType: String? = null
        var nameField: TextField? = null
        var fos: ByteArrayOutputStream? = null

        override fun receiveUpload(filename: String,
                                   mimeType: String): OutputStream {
            val outputStream = ByteArrayOutputStream()
            this.fos = outputStream
            this.filename = filename
            this.mimeType = mimeType
            return outputStream
        }

        override fun uploadSucceeded(event: Upload.SucceededEvent) {
            val uploadedFile = this.fos!!.toByteArray()
            if (uploadedFile.isNotEmpty()) {
                uploadedFileService.saveAndFlush(UploadedFile(
                        // ifs are an expression and can return a value - in Kotlin there is no conditional operator "statement ? if-true : if-false",
                        // instead we have to use "if (statement) if-true else if-false"
                        name = if (nameField?.value.isNullOrBlank().not()) nameField?.value else filename,
                        data = uploadedFile))
                refresh()
            }
        }
    }
}