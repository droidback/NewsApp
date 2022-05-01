package uz.gita.newsappwithapi.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.gita.newsappwithapi.data.local.dao.NewsDao
import uz.gita.newsappwithapi.data.local.entity.NewsEntity


@Database(entities = [NewsEntity::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun getNewsDao() : NewsDao
}