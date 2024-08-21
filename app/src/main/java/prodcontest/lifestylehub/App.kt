package prodcontest.lifestylehub

import android.app.Application
import com.yandex.mapkit.MapKitFactory
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import prodcontest.data.di.dataModule
import prodcontest.lifestylehub.presentation.di.presentationModule
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        MapKitFactory.setApiKey("96832af2-ada2-4b08-922f-904309a622e8")

        Timber.plant(Timber.DebugTree())

        startKoin {
            androidContext(this@App)
            modules(listOf(dataModule, presentationModule, prodcontest.auth.data.di.dataModule))
        }
    }
}