package dev.robinsond.choremanagement.choremanagement.users

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document


@Document(collection = "users")
data class User(
    @Id val id: String? = null,
    val username: String,
    val password: String,
    val roles: Set<String>
)

data class ChoreTrackerUser(
    val username: String,
    val roles: Set<String>
)
