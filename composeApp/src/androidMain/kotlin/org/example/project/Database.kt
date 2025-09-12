package org.example.project

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import org.example.project.room.AppDatabase


private lateinit var appContext: Context

fun initDatabase(context: Context) {
    appContext = context.applicationContext
}

actual fun provideDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    check(::appContext.isInitialized) { "Call initDatabase(context) before using database" }
    return Room.databaseBuilder(appContext, AppDatabase::class.java, "my_room.db")
}