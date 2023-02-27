package dev.robinsond.choremanagement.choremanagement.config

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.core.env.Environment
import org.springframework.data.mongodb.MongoDatabaseFactory
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
import org.springframework.data.mongodb.core.convert.MappingMongoConverter
import org.springframework.data.mongodb.core.convert.MongoCustomConversions
import org.springframework.data.mongodb.gridfs.GridFsTemplate
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import java.nio.charset.StandardCharsets

@Configuration
@EnableMongoRepositories
class MongoConfig(@Autowired val environment: Environment): AbstractMongoClientConfiguration() {

    override fun getDatabaseName(): String {
        return environment.getRequiredProperty("spring.data.mongodb.database")
    }


    override fun mongoClient(): MongoClient {
        val connectionString = ConnectionString(environment.getRequiredProperty("spring.data.mongodb.uri"))
        val mongoClientSettings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build()
        return MongoClients.create(mongoClientSettings)
    }

    @Bean
    fun mongoCustomConversions(): MongoCustomConversions {
        return MongoCustomConversions(listOf(ByteArrayToStringConverter()))
    }

    @Bean
    fun gridFsTemplate(factory: MongoDatabaseFactory, converter: MappingMongoConverter): GridFsTemplate {
        return GridFsTemplate(factory, converter)
    }

    inner class ByteArrayToStringConverter : Converter<ByteArray, String> {
        override fun convert(source: ByteArray): String {
            return String(source, StandardCharsets.UTF_8)
        }
    }


}
