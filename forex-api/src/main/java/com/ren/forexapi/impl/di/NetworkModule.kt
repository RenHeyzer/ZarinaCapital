package com.ren.forexapi.impl.di

import com.ren.forexapi.api.di.ForexBaseUrl
import com.ren.forexapi.api.service.changepassword.ChangePasswordApiService
import com.ren.forexapi.impl.clients.AuthenticatedRetrofitClient
import com.ren.forexapi.impl.clients.DefaultRetrofitClient
import com.ren.forexapi.impl.interceptors.TokenInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun provideDefaultRetrofitClient(
        @ForexBaseUrl baseUrl: String
    ) = DefaultRetrofitClient(baseUrl)


    @Provides
    @Singleton
    fun provideAuthenticatedRetrofitClient(
        @ForexBaseUrl baseUrl: String,
        tokenInterceptor: TokenInterceptor
    ) = AuthenticatedRetrofitClient(baseUrl, tokenInterceptor)

    @Provides
    @Singleton
    fun provideAuthApiService(retrofitClient: DefaultRetrofitClient) =
        retrofitClient.authApiService

    @Provides
    @Singleton
    fun provideNewsApiService(retrofitClient: DefaultRetrofitClient) =
        retrofitClient.newsApiService

    @Provides
    @Singleton
    fun provideCoursesApiService(retrofitClient: DefaultRetrofitClient) =
        retrofitClient.coursesApiService

    @Provides
    @Singleton
    fun provideReviewsApiService(retrofitClient: DefaultRetrofitClient) =
        retrofitClient.reviewsApiService

    @Provides
    @Singleton
    fun provideProfileApiService(retrofitClient: AuthenticatedRetrofitClient) =
        retrofitClient.profileApiService

    @Provides
    @Singleton
    fun providesSchedulesApiService(retrofitClient: DefaultRetrofitClient) =
        retrofitClient.schedulesApiService

    @Provides
    @Singleton
    fun providesLessonsApiService(retrofitClient: DefaultRetrofitClient) =
        retrofitClient.lessonsApiService

    @Provides
    @Singleton
    fun provideResetPasswordApiService(retrofitClient: DefaultRetrofitClient) =
        retrofitClient.resetPasswordApiService

    @Provides
    @Singleton
    fun provideChangePasswordApiService(retrofitClient: AuthenticatedRetrofitClient) =
        retrofitClient.changePassword
}