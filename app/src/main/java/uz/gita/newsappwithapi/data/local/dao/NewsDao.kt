package uz.gita.newsappwithapi.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import uz.gita.newsappwithapi.data.local.entity.NewsEntity

@Dao
interface NewsDao {
    @Query("Select * from NewsEntity Where NewsEntity.category = :category")
    suspend fun getNewsByCategory(category : String) : List<NewsEntity>

    @Insert
    suspend fun insertAllNews(list: List<NewsEntity>)

    @Query("DELETE FROM NewsEntity Where category = :category")
    suspend fun deleteNewsData(category: String)
}