package rj.vaadinKotlinCoop.entity

import javax.persistence.*

@Entity
@Table(name = "account")
data class Account(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    val id: Long = -1L,

    val number: String = "",

    val name: String = "")