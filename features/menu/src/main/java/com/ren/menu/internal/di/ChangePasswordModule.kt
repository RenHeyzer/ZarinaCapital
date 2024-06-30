package com.ren.menu.internal.di

import com.ren.common.Mapper
import com.ren.forexapi.api.models.changepassword.ChangePasswordDto
import com.ren.menu.internal.data.mappers.ChangePasswordMapper
import com.ren.menu.internal.data.repositories.ChangePasswordRepository
import com.ren.menu.internal.domain.entities.changepassword.ChangePassword
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
interface ChangePasswordModule {
    @Binds
    fun bindChangePasswordMapper(changePasswordMapper: ChangePasswordMapper): Mapper<ChangePasswordDto, ChangePassword>

//    @Binds
//    @ViewModelScoped
//    fun bindChangePasswordRepository(repositoryImpl: ChangePasswordRepository): ChangePassword

}