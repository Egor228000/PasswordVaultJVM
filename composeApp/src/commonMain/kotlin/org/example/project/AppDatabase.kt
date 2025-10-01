package org.example.project

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import org.example.project.room.*

@Database(
    entities = [PasswordCard::class, PinCode::class, Entry::class, SecretKey::class],
    version = 7
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun passwordCardDao(): PasswordCardDao
}

expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}

object DatabaseManager {
    private val database: AppDatabase by lazy {
        AppDatabaseConstructor.initialize()
    }

    private val dao: PasswordCardDao by lazy {
        database.passwordCardDao()
    }

    suspend fun insertPasswordCard(
        name: String,
        login: String,
        description: String,
        password: String,
        avatar: Int
    ) {
        dao.insert(
            PasswordCard(
                name = name,
                login = login,
                description = description,
                password = password,
                avatar = avatar
            )
        )
    }
    suspend fun getAllPasswordCards(): List<PasswordCard> {
        return dao.getAll()
    }

    suspend fun getPasswordCardById(id: Long): PasswordCard? {
        return dao.getById(id)
    }
}
