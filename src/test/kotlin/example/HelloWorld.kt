package example

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName

@Testcontainers
class HelloWorld {

    @Container
    val container: PostgreSQLContainer<*> = PostgreSQLContainer(DockerImageName.parse("postgres:latest"))
        .withDatabaseName("exposed-test")
        .withUsername("exposed")
        .withPassword("password")


    @Test
    fun `container is ready`() {
        assertTrue(container.isRunning)
    }

    @Test
    fun `can create schema`() {
        Database.connect(
            container.jdbcUrl,
            driver = "org.postgresql.Driver",
            user = "exposed",
            password = "password"
        )
        transaction {
            SchemaUtils.create(Talks)
        }
    }
}