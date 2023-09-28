package jp.matsuura.qiitasearchandroidapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.matsuura.qiitasearchandroidapp.data.api.QiitaApi
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {

    @Singleton
    @Provides
    fun provideQiitaApi(retrofit: Retrofit): QiitaApi {
        return retrofit.create(QiitaApi::class.java)
    }

}