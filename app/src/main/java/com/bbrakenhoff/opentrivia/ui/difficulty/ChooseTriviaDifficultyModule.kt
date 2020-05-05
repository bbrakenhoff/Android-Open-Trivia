package com.bbrakenhoff.opentrivia.ui.difficulty

import com.bbrakenhoff.opentrivia.KoinModule
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object ChooseTriviaDifficultyModule : KoinModule {
    override fun start(): Module = module {

        viewModel { ChooseTriviaDifficultyViewModel() }
    }
}
