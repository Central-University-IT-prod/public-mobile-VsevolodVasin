package prodcontest.lifestylehub


import android.Manifest
import android.content.Context
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.GrantPermissionRule
import com.example.compose.AppTheme
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.java.KoinJavaComponent
import prodcontest.domain.weather.model.WeatherModel
import prodcontest.domain.weather.repository.WeatherRepository
import prodcontest.lifestylehub.presentation.home.weather.WeatherWidget
import prodcontest.lifestylehub.presentation.home.weather.WeatherWidgetTags
import prodcontest.lifestylehub.semantics.DrawableId

@RunWith(JUnit4::class)
class UITestsWeatherWidget {

    private lateinit var context: Context

    @Before
    fun setup() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
    }

    @get:Rule
    val composeTestRule = createComposeRule()

    @get:Rule
    var mRuntimePermissionRule: GrantPermissionRule? =
        GrantPermissionRule.grant(Manifest.permission.ACCESS_COARSE_LOCATION)

    private val weatherRepository : WeatherRepository by KoinJavaComponent.inject(
        WeatherRepository::class.java
    )
    private lateinit var weather : WeatherModel

    @Before
    fun setContent() {
        runBlocking {
            weather = weatherRepository.getWeather()
            composeTestRule.setContent {
                AppTheme {
                    WeatherWidget(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }

    @Test
    fun city() {
        composeTestRule.onNodeWithTag(WeatherWidgetTags.CITY)
            .assertIsDisplayed()
            .assert(hasText(weather.city))
    }

    @Test
    fun weatherName() {
        composeTestRule.onNodeWithTag(WeatherWidgetTags.WEATHER)
            .assertIsDisplayed()
            .assert(hasText(weather.weather.name))
    }

    @Test
    fun temperature() {
        composeTestRule.onNodeWithTag(WeatherWidgetTags.TEMPERATURE)
            .assertIsDisplayed()
            .assert(hasText("${weather.temperature.real} °C"))
    }

    @Test
    fun temperatureFeel() {
        composeTestRule.onNodeWithTag(WeatherWidgetTags.TEMPERATURE_FEEL)
            .assertIsDisplayed()
            .assert(hasText("ощущается как ${weather.temperature.feel} °C"))
    }

    @Test
    fun temperatureRange() {
      composeTestRule.onNodeWithTag(WeatherWidgetTags.TEMPERATURE_RANGE)
            .assertIsDisplayed()
            .assert(hasText("${weather.temperature.weatherMin}°C-${weather.temperature.weatherMax}°C"))
    }

    @Test
    fun icon() {
        val correctIconId = context.resources.getIdentifier(weather.weather.icon, "drawable", context.packageName)
        composeTestRule.onNodeWithTag(WeatherWidgetTags.ICON)
            .assertIsDisplayed()
        composeTestRule.onNode(hasDrawable(correctIconId))
            .assertIsDisplayed()
    }


    private fun hasDrawable(@DrawableRes id: Int): SemanticsMatcher =
        SemanticsMatcher.expectValue(DrawableId, id)

}