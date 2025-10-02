package org.example.project.room

import androidx.room.*




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
    @Insert
    suspend fun insertKey(key: SecretKey): String

    @Insert
    suspend fun insertPinCode(code: PinCode): String
    @Update
    suspend fun updatePinCode(code: PinCode)



    @Insert
    suspend fun insertEntry(entry: Entry)
    @Update
    suspend fun updateEntry(code: PinCode)
}
