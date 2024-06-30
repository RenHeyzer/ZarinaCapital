package com.ren.menu.internal.data.repositories

import com.ren.common.AppDispatchers
import com.ren.common.Mapper
import com.ren.data.base.BaseRepository
import com.ren.forexapi.api.models.changepassword.ChangePasswordDto
import com.ren.forexapi.api.models.news.NewsDTO
import com.ren.forexapi.api.service.changepassword.ChangePasswordApiService
import com.ren.menu.internal.domain.entities.changepassword.ChangePassword
import com.ren.menu.internal.domain.entities.news.News
import com.ren.menu.internal.domain.repositories.ChangePasswordRepository
import com.ren.presentation.utils.UIState
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ChangePasswordRepository @Inject constructor(
    private val appDispatchers: AppDispatchers,
    private val changePasswordMapper: Mapper<ChangePasswordDto, ChangePassword>,
    private val changePasswordApiService: ChangePasswordApiService
) : BaseRepository(appDispatchers) {

    suspend fun changePassword(data: ChangePassword): UIState<Unit> {
        return withContext(appDispatchers.io) {
            try {
                changePasswordApiService.changePassword(changePasswordMapper.from(data))
                UIState.Success(Unit)
            } catch (e: Exception) {
                UIState.Error(e)
            }
        }
    }
}




