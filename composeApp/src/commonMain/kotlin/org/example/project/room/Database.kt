package org.example.project.room

import androidx.room.*
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers

@Database(entities = [PasswordCard::class], version = 1)
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
