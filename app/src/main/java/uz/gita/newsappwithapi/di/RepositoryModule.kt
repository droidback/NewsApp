package uz.gita.newsappwithapi.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.newsappwithapi.domain.repository.NewsRepository
import uz.gita.newsappwithapi.domain.repository.impl.NewsRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @[Binds Singleton]
    fun getNewsRepository(impl : NewsRepositoryImpl) : NewsRepository
}