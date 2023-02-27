package dev.robinsond.choremanagement.choremanagement

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(*["dev.robinsond.choremanagement.choremanagement.chores", "dev.robinsond.choremanagement.choremanagement.users", "dev.robinsond.choremanagement.choremanagement.security"])
class ChoreManagementApplication

fun main(args: Array<String>) {
	runApplication<ChoreManagementApplication>(*args)
}
