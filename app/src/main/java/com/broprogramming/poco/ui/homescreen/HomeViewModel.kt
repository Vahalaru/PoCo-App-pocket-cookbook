package com.broprogramming.poco.ui.homescreen

import androidx.lifecycle.ViewModel
import com.broprogramming.poco.repository.RecipesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: RecipesRepository
): ViewModel() {
}