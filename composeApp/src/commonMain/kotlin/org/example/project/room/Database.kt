package org.example.project.room

import androidx.room.*
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.example.project.provideDatabaseBuilder

@Database(entities = [PasswordCard::class, PinCode::class, Entry::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun passwordCardDao(): PasswordCardDao

    companion object {
        fun getRoomDatabase(
            builder: RoomDatabase.Builder<AppDatabase>
        ): AppDatabase {
            return builder
                .setDriver(BundledSQLiteDriver())
                .setQueryCoroutineContext(Dispatchers.IO)
                .build()
        }
    }
}
object DatabaseManager {
    private val db: AppDatabase by lazy {
        AppDatabase.getRoomDatabase(provideDatabaseBuilder())
    }

    private val dao = db.passwordCardDao()

    fun insertPasswordCard(name: String, description: String, password: String, avatar: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.insert(PasswordCard(name = name, description = description, password = password, avatar = avatar))
        }
    }

    fun getAllCards(callback: (List<PasswordCard>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val data = dao.getAll()
            callback(data)
        }
    }
}
@Dao
interface PasswordCardDao {
    @Query("SELECT * FROM passwordcard")
    suspend fun getAll(): List<PasswordCard>

    @Query("SELECT * FROM passwordcard WHERE id = :id")
    suspend fun getById(id: Long): PasswordCard?

    @Insert
    suspend fun insert(card: PasswordCard): Long

    @Update
    suspend fun update(card: PasswordCard)

    @Delete
    suspend fun delete(card: PasswordCard)

    @Query("DELETE FROM passwordcard WHERE id = :id")
    suspend fun deleteById(id: Long)
}
