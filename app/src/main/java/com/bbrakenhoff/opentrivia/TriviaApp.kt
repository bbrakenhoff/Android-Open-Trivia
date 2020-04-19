package com.bbrakenhoff.opentrivia

import android.app.Application
import com.bbrakenhoff.opentrivia.networking.NetworkModule
import com.bbrakenhoff.opentrivia.ui.ChooseCategoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TriviaApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@TriviaApp)
            modules(listOf(NetworkModule.start(), ChooseCategoryModule.start()))
        }
    }
}
