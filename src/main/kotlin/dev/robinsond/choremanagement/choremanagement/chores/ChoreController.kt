package dev.robinsond.choremanagement.choremanagement.chores

import org.bson.Document
import org.bson.types.ObjectId
import org.springframework.data.mongodb.gridfs.GridFsTemplate
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/chores")
class ChoreController(val choreRepository: ChoreRepository, val gridFsTemplate: GridFsTemplate) {

    @GetMapping("/")
    fun getAllChores(): List<Chore?> {
        val chores = choreRepository.findAll().toList()
        println("Chores: $chores")
        return chores
    }

    @PostMapping("/done")
    fun createChore(chore: Chore, @RequestParam("images") images: List<MultipartFile>): Chore {
        val username = SecurityContextHolder.getContext().authentication.name

        val imageIds = mutableListOf<String>()
        for (image in images) {
            val fileId = ObjectId()
            val filename = image.originalFilename ?: fileId.toString()
            val metadata = Document().apply {
                append("contentType", image.contentType)
                append("length", image.size)
            }
            val file = gridFsTemplate.store(image.inputStream, filename, metadata)
            imageIds.add(file.toHexString())
            println(" ${gridFsTemplate.getResource(file.toHexString())}")
        }
        val savedChore = choreRepository.save(chore.copy(assignedTo = username, imageIds = imageIds))
        println("Savied Chore: $savedChore for user: $username")
        return savedChore
    }
}
