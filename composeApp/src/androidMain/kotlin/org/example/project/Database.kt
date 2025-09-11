package org.example.project

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import org.example.project.room.AppDatabase


fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<AppDatabase> {
    val appContext = context.applicationContext
    return Room.databaseBuilder(appContext, AppDatabase::class.java, "my_room.db")
}