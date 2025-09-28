package org.example.project

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.example.project.room.Entry
import org.example.project.room.PasswordCard
import org.example.project.room.PasswordCardDao
import org.example.project.room.PinCode
import org.example.project.room.SecretKey

@Database(
    entities = [PasswordCard::class, PinCode::class, Entry::class, SecretKey::class],
    version = 1
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

    fun insertPasswordCard(
        name: String,
        description: String,
        password: String,
        avatar: String
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.insert(
                PasswordCard(
                    name = name,
                    description = description,
                    password = password,
                    avatar = avatar
                )
            )
        }
    }
}
