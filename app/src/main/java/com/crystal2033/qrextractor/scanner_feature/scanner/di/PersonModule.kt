package com.crystal2033.qrextractor.scanner_feature.scanner.di

import android.content.Context
import com.crystal2033.qrextractor.core.ApiInfo
import com.crystal2033.qrextractor.scanner_feature.scanner.data.Converters
import com.crystal2033.qrextractor.scanner_feature.scanner.data.remote.api.PersonApi
import com.crystal2033.qrextractor.scanner_feature.scanner.data.repository.PersonRepositoryImpl
import com.crystal2033.qrextractor.scanner_feature.scanner.data.util.GsonParser
import com.crystal2033.qrextractor.scanner_feature.scanner.domain.repository.PersonRepository
import com.crystal2033.qrextractor.scanner_feature.scanner.domain.use_case.concrete_use_case.GetPersonFromQRCodeUseCase
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersonModule {

    @Provides
    @Singleton
    fun provideGetPersonUseCase(repository: PersonRepository): GetPersonFromQRCodeUseCase {
        return GetPersonFromQRCodeUseCase(repository)
    }

    @Provides
    @Singleton
    fun providePersonRepository(personApi: PersonApi, @ApplicationContext context: Context): PersonRepository {
        return PersonRepositoryImpl(personApi, context)
    }

    @Provides
    @Singleton
    fun providePersonApi(okHttpClient: OkHttpClient) : PersonApi {
        return Retrofit.Builder()
            .baseUrl(ApiInfo.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(PersonApi::class.java)
    }




    @Provides
    @Singleton
    fun provideConverter(): Converters {
        return Converters(GsonParser(Gson()))
    }


}