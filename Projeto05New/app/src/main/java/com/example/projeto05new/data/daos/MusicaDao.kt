package com.example.projeto05new.data.daos

import androidx.room.*
import com.example.projeto05new.data.models.Musica

@Dao
interface MusicaDao {
    @Query("SELECT * FROM musica")
    fun getAll(): List<Musica>

    @Query("SELECT * FROM musica WHERE id IN (:musicasId)")
    fun loadAllByIds(musicasId: IntArray): List<Musica>

    @Query("SELECT * FROM musica WHERE nome LIKE :first LIMIT 1")
    fun findByName(first: String) : Musica

    @Insert
    fun insert(musica:Musica)

    @Delete
    fun delete(musica:Musica)


}