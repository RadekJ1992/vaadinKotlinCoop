package rj.vaadinKotlinCoop.entity

import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "balance")
data class Balance(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    val id: Long = -1L,

    @ManyToOne
    @JoinColumn
    val account: Account? = null,

    val debit: BigDecimal = BigDecimal.ZERO,

    val credit: BigDecimal = BigDecimal.ZERO,

    val period: Int = 0,

    @Column(name = "incremental_debit")
    val incrementalDebit: BigDecimal = BigDecimal.ZERO,

    @Column(name = "incremental_credit")
    val incrementalCredit: BigDecimal = BigDecimal.ZERO,

    // add some more values so displayed grid is large
    @Column(name = "value_v")
    val valueV: BigDecimal = BigDecimal.ZERO,

    @Column(name = "value_x")
    val valueX: BigDecimal = BigDecimal.ZERO,

    @Column(name = "value_y")
    val valueY: BigDecimal = BigDecimal.ZERO,

    @Column(name = "value_z")
    val valueZ: BigDecimal = BigDecimal.ZERO)