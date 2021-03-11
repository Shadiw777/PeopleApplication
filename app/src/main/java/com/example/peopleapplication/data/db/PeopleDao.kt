package com.example.peopleapplication.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.peopleapplication.data.model.People
import kotlinx.coroutines.flow.Flow

@Dao
interface PeopleDao {

    @Query("SELECT * FROM People")
    fun getAll(): Flow<List<People>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(people: People)

    @Query("DELETE FROM People")
    suspend fun deleteAll()

    @Query("SELECT * FROM People WHERE id=:id")
    fun find(id: Int): Flow<People>

    @Query("SELECT * FROM People WHERE name LIKE '%' || :name || '%'")
    fun findBy(name: String): Flow<List<People>>

}