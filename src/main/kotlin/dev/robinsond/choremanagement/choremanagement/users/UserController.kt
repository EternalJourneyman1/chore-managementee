package dev.robinsond.choremanagement.choremanagement.users

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/api/user")
class UserController {
    @GetMapping
    fun getCurrentUser(principal: Principal): Any? {
        val authentication = SecurityContextHolder.getContext()?.authentication
        val username = authentication?.name ?: ""
        val authorities = authentication?.authorities?.map { it.authority } ?: emptyList()
        println("Returning current user: $username")
        return ChoreTrackerUser(username, authorities.toSet())
    }
}
