package org.example.project

import androidx.room.RoomDatabase
import org.example.project.room.AppDatabase

expect fun provideDatabaseBuilder(): RoomDatabase.Builder<AppDatabase>