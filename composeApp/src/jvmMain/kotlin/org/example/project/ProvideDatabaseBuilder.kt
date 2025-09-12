package org.example.project

import androidx.room.Room
import androidx.room.RoomDatabase
import org.example.project.room.AppDatabase
import java.io.File
actual fun provideDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {

    val dbFile = File(System.getProperty("java.io.tmpdir"), "my_room.db")
    return Room.databaseBuilder<AppDatabase>(name = dbFile.absolutePath)
}