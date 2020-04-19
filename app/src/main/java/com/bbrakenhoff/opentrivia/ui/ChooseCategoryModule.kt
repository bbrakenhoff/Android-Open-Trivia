package com.bbrakenhoff.opentrivia.ui

import com.bbrakenhoff.opentrivia.KoinModule
import org.koin.androidx.viewmodel.compat.ViewModelCompat.viewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object ChooseCategoryModule : KoinModule {
    override fun start(): Module = module {

        viewModel { ChooseCategoryViewModel() }
    }
}
