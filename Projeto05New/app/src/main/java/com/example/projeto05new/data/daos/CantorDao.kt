package com.example.projeto05new.data.daos

import androidx.room.*
import com.example.projeto05new.data.models.Cantor

@Dao
interface CantorDao {
    @Query("SELECT * FROM cantor")
    fun getAll(): List<Cantor>

    @Query("SELECT * FROM cantor WHERE uid IN (:cantoresIds)")
    fun loadAllByIds(cantoresIds: IntArray): List<Cantor>

    @Query("SELECT * FROM cantor WHERE primeiroNome LIKE :first AND " + "ultimoNome LIKE :last LIMIT 1")
    fun findByName(first: String, last: String) : Cantor

    @Insert
    fun insert(cantor:Cantor)

    @Delete
    fun delete(cantor:Cantor)


}