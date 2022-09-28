package com.sayaa.todolistjetpack.di

import android.app.Application
import androidx.room.Room
import com.sayaa.todolistjetpack.data.TodoDB
import com.sayaa.todolistjetpack.data.TodoRepository
import com.sayaa.todolistjetpack.data.TodoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app : Application):TodoDB{
        return Room.databaseBuilder(
            app,
            TodoDB::class.java,
            "db_name"
        ).build()
    }

    @Provides
    @Singleton
    fun provideRepository(db:TodoDB): TodoRepository {
        return TodoRepositoryImpl(db.todoDao)
    }
}