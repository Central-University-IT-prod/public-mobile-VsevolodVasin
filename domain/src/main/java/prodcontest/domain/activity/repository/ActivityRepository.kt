package prodcontest.domain.activity.repository

import prodcontest.domain.activity.model.ActivityModel

interface ActivityRepository {

    suspend fun getActivity(easy : Boolean): ActivityModel

}