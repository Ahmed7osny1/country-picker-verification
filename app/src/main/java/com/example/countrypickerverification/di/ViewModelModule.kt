package com.example.countrypickerverification.di

import com.example.countrypickerverification.presentation.screen.sign_up.SignupViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SignupViewModel(get()) }
}