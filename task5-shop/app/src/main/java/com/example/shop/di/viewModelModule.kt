package com.example.shop.di

import com.example.shop.presentation.cart.CartViewModel
import com.example.shop.presentation.products.ProductsViewModel
import com.example.shop.presentation.products.details.ProductDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ProductsViewModel(get()) }
    viewModel { CartViewModel(get()) }
    viewModel { ProductDetailsViewModel(get()) }
}