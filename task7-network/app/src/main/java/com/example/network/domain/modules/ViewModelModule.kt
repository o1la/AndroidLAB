package com.example.network.domain.modules

import com.example.network.ui.screens.category.CategoryViewModel
import com.example.network.ui.screens.product.ProductViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CategoryViewModel(get(), get()) }
    viewModel { ProductViewModel(get(), get()) }
}