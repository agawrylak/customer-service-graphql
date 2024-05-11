package edu.agawrylak.customerservicegraphql.model

import jakarta.persistence.*

@Entity
@Table(name = "customers")
data class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    val id: Long = 0,

    @Column(nullable = false, length = 100)
    val name: String,

    @Column(nullable = false, length = 100, unique = true)
    val email: String,

    @Column(length = 15)
    val phone: String?
)