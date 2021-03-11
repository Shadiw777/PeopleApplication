package com.example.peopleapplication.data.repository

import com.example.peopleapplication.data.db.PeopleDao
import com.example.peopleapplication.data.model.People
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PeopleRepository @Inject constructor(private val peopleDao: PeopleDao) {

    fun getAllPeople(): Flow<List<People>> = peopleDao.getAll()

    /**
     * Adds a new people info on peopleList
     */
    suspend fun insertPeople(people: People) {
        peopleDao.insert(people)
    }

    /**
     * Finds people with specific id
     */
    fun findPeople(id: Int): Flow<People> = peopleDao.find(id)

    /**
     * Find people by name
     */
    fun findPeopleByName(name: String): Flow<List<People>> = peopleDao.findBy(name)

}