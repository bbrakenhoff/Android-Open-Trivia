package com.bbrakenhoff.opentrivia.ui

import com.bbrakenhoff.opentrivia.KoinModule
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object ChooseTriviaCategoryModule : KoinModule {
    override fun start(): Module = module {

        viewModel { ChooseTriviaCategoryViewModel(get()) }
    }
}
