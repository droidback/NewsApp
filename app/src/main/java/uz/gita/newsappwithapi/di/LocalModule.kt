package uz.gita.newsappwithapi.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.gita.newsappwithapi.data.local.MyDatabase
import uz.gita.newsappwithapi.data.local.dao.NewsDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

    @[Provides Singleton]
    fun getDatabase(@ApplicationContext context: Context): MyDatabase
    = Room.databaseBuilder(context, MyDatabase::class.java, "NewsDatabase").build()

    @[Provides Singleton]
    fun getNewsDao(database: MyDatabase): NewsDao = database.getNewsDao()
}