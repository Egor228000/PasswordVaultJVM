package org.example.project

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabaseConstructor

actual object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    private lateinit var instance: AppDatabase

    fun build(context: Context): AppDatabase {
        if (!::instance.isInitialized) {
            instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "my_room.db"
            )
                .fallbackToDestructiveMigration()
                .build()
        }
        return instance
    }

    actual override fun initialize(): AppDatabase {
        check(::instance.isInitialized) { "AppDatabase must be built with context first" }
        return instance
    }
}
