package com.bbrakenhoff.opentrivia.repository

import com.bbrakenhoff.opentrivia.KoinModule
import org.koin.core.module.Module
import org.koin.dsl.module

object RepositoryModule : KoinModule {
    override fun start(): Module = module {
        single { TriviaCategoryRepository(get(), get()) }
    }
}
