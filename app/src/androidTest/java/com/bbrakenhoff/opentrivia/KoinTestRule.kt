package com.bbrakenhoff.opentrivia

import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module

class KoinTestRule(private val testModule: Module) : TestWatcher() {

    override fun starting(description: Description?) {
        super.starting(description)

        loadKoinModules(testModule)
    }

    override fun finished(description: Description?) {
        super.finished(description)

        unloadKoinModules(testModule)
    }
}
