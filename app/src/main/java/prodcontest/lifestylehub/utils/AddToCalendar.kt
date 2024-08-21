package prodcontest.lifestylehub.utils

import android.content.Context
import android.content.Intent
import android.provider.CalendarContract
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent
import prodcontest.domain.leisure.model.LeisureModel
import prodcontest.domain.recommendation.repository.RecommendationsRepository
import prodcontest.lifestylehub.presentation.utils.uiDateFormat

fun LeisureModel.addToCalendar(context : Context) = CoroutineScope(Dispatchers.IO).launch {
        val recommendationsRepository : RecommendationsRepository by KoinJavaComponent.inject(
                RecommendationsRepository::class.java
        )

        val intent = Intent(Intent.ACTION_EDIT)
        intent.type = "vnd.android.cursor.item/event"
        val date = uiDateFormat.parse(this@addToCalendar.date)
        if (date != null) {
            intent.putExtra(CalendarContract.Events.DTSTART, date.time)
        }
        intent.putExtra(CalendarContract.Events.ALL_DAY, true)
        intent.putExtra(CalendarContract.Events.TITLE, this@addToCalendar.name)
        intent.putExtra(CalendarContract.Events.DESCRIPTION, this@addToCalendar.description)
        if (this@addToCalendar.recommendation != null) {
                val recommendation = recommendationsRepository.getRecommendationDetails(this@addToCalendar.recommendation!!)
                intent.putExtra(CalendarContract.Events.EVENT_LOCATION, recommendation.address)
        }
        withContext(Dispatchers.Main) { context.startActivity(intent) }
}