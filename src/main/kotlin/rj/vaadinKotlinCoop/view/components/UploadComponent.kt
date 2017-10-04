package rj.vaadinKotlinCoop.view.components

import com.vaadin.ui.Upload
import rj.vaadinKotlinCoop.view.i18n.I18nHelper

class UploadComponent() : Upload(){
    init{
        val i18nHelper = I18nHelper(I18nHelper.ResourceBundleName.FILE_UPLOAD)
        buttonCaption = i18nHelper.get("file_upload_caption")
    }

    constructor(caption: String?, uploadReceiver: Receiver) : this() {
        setCaption(caption)
        receiver = uploadReceiver
    }
}
