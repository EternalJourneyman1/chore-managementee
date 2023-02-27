package dev.robinsond.choremanagement.choremanagement.chores

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.core.context.SecurityContextHolder

@Document(collection = "chores")
data class Chore(
    @Id
    val id: String? = null,
    var name: String,
    var location: String,
    var assignedTo: String? = SecurityContextHolder.getContext().authentication.name ?: null,
    var completed: Boolean = false,
    var imageIds: List<String> = emptyList()
)
