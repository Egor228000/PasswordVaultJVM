package org.example.project.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("passwordcard")
data class PasswordCard(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val description: String,
    val password: String,
    val avatar: Int
)