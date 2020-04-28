package com.bbrakenhoff.opentrivia

import android.app.Application
import com.bbrakenhoff.opentrivia.api.NetworkModule
import com.bbrakenhoff.opentrivia.database.DatabaseModule
import com.bbrakenhoff.opentrivia.repository.RepositoryModule
import com.bbrakenhoff.opentrivia.ui.ChooseTriviaCategoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

@Suppress("unused")
class TriviaApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@TriviaApp)
            modules(
                listOf(
                    NetworkModule.start(),
                    DatabaseModule.start(),
                    RepositoryModule.start(),
                    ChooseTriviaCategoryModule.start()
                )
            )
        }
    }
}
