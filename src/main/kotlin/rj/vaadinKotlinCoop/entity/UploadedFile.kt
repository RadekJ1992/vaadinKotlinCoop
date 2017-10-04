package rj.vaadinKotlinCoop.entity

import javax.persistence.*

@Entity
@Table(name = "uploaded_file")
class UploadedFile (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    val id: Int = -1,

    @Column(name = "name")
    val name: String? = null,

    @Lob
    @Column(name = "data", length = 100000)
    val data: ByteArray? = null
)