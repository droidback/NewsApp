package uz.gita.newsappwithapi.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.gita.newsappuseroom.data.model.NewsData

@Entity
data class NewsEntity(
    val image: String,
    val read_more: String?,
    val author: String,
    val description: String,
    val inshorts_link: String,
    @PrimaryKey
    val title: String,
    val timestamp: String,
    val category : String
)

fun NewsEntity.toNewsData() : NewsData = NewsData(
    this.image,
    this.read_more,
    this.author,
    this.description,
    this.title
)