package dev.robinsond.choremanagement.choremanagement.users

import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface UserRepository : MongoRepository<User?, Long?> {
    fun findByUsername(username: String?): Optional<User?>?
}
