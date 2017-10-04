package rj.vaadinKotlinCoop.service

import org.springframework.stereotype.Service
import rj.vaadinKotlinCoop.persistence.AccountRepository
import rj.vaadinKotlinCoop.persistence.UploadedFileRepositody
import javax.inject.Inject

@Service
class UploadedFileService @Inject constructor(private val uploadedFileRepositody: UploadedFileRepositody)
    : UploadedFileRepositody by uploadedFileRepositody // delegated property, this service acts only as a proxy