package com.example.projeto05new.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cantor(
    @PrimaryKey(autoGenerate = true) val uid:Int = 0,
    val primeiroNome: String?,
    val ultimoNome: String?,
    val idade: String?
)