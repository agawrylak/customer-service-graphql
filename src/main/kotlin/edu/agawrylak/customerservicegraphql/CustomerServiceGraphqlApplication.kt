package edu.agawrylak.customerservicegraphql

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.ServletComponentScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@ComponentScan("edu.*")
class CustomerServiceGraphqlApplication

fun main(args: Array<String>) {
	runApplication<CustomerServiceGraphqlApplication>(*args)
}
