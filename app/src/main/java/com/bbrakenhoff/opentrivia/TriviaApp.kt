package com.bbrakenhoff.opentrivia

import android.app.Application
import com.bbrakenhoff.opentrivia.api.NetworkModule
import com.bbrakenhoff.opentrivia.ui.ChooseTriviaCategoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class TriviaApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@TriviaApp)
            modules(listOf(NetworkModule.start(), ChooseTriviaCategoryModule.start()))
        }
    }
}
