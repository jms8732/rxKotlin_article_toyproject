package com.example.myapplication.di

import com.example.myapplication.ui.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModules = module {
    viewModel { MainActivityViewModel()  }
}