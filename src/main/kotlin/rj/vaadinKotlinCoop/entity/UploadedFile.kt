package rj.vaadinKotlinCoop.entity

import javax.persistence.*

@Entity
@Table(name = "uploaded_file")
class UploadedFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private val id: Int = 0

    @Column(name = "name")
    private val name: String? = null

    @Lob
    @Column(name = "data", length = 100000)
    private val data: ByteArray? = null
}
