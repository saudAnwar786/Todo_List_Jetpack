package com.sayaa.todolistjetpack.util

sealed class UiEvents() {

    object PopBackStack: UiEvents()
    data class Navigate(
        val route: String
    ):UiEvents()
    data class ShowSnackbar(
        val message: String,
        val actions: String? = null
    ):UiEvents()
}