package com.example.peopleapplication.di

import android.content.Context
import com.example.peopleapplication.data.db.PeopleDao
import com.example.peopleapplication.data.db.PeopleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun providePeopleDatabase(@ApplicationContext context: Context): PeopleDatabase {
        return PeopleDatabase.getInstance(context)
    }

    @Provides
    fun providePeopleDao(peopleDatabase: PeopleDatabase): PeopleDao {
        return peopleDatabase.peopleDao()
    }

}