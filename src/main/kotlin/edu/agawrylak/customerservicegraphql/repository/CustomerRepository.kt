package edu.agawrylak.customerservicegraphql.repository

import edu.agawrylak.customerservicegraphql.model.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : JpaRepository<Customer, Long>