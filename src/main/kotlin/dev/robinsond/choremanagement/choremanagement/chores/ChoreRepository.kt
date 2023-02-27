package dev.robinsond.choremanagement.choremanagement.chores

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
interface ChoreRepository: MongoRepository<Chore?, Long?> {
    fun findChoreByAssignedTo(username: String?): Optional<List<Chore>?>?
}
