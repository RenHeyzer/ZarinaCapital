package com.ren.menu.internal.domain.repositories

import android.provider.CalendarContract.Attendees
import com.ren.common.Either
import com.ren.menu.internal.domain.entities.attandences.Attandences
import com.ren.menu.internal.domain.entities.news.News
import kotlinx.coroutines.flow.Flow

interface AttandencesRepository {

    fun fetchAttandences(): Flow<Either<Throwable, List<Attandences>>>
}