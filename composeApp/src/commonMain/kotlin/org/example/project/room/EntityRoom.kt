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
@Entity("pincode")
data class PinCode(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val pinCode: String
)

@Entity("entry")
data class Entry(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val entry: Boolean
)

@Entity("secretKey")
data class SecretKey(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val key: String
)