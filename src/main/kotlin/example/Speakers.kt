package example

import org.jetbrains.exposed.sql.Table

object Speakers: Table() {
    val id = uuid("speaker_id")
    val name = varchar("name", 50)
    val bio = varchar("bio", 1000)
    val twitter = varchar("twitter", 20).default("")
    override val primaryKey = PrimaryKey(id)
}