package edu.agawrylak.customerservicegraphql.datafetcher

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import edu.agawrylak.customerservicegraphql.model.Customer
import edu.agawrylak.customerservicegraphql.repository.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired

@DgsComponent
class CustomerDatafetcher {

    @Autowired
    lateinit var customerRepository: CustomerRepository

    @DgsQuery
    fun Customers(): List<Customer> {
        val customers = customerRepository.findAll()
        return customers
    }

}