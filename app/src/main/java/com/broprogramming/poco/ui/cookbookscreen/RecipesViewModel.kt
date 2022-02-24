package com.broprogramming.poco.ui.cookbookscreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.broprogramming.poco.model.Recipes
import com.broprogramming.poco.repository.RecipesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val repo: RecipesRepository
) : ViewModel() {

    var loading = mutableStateOf(false)

    val itemsFlow = flow {
        loading.value = true
        val rList = repo.queryRecipesByName.get().await().map { doc ->
            Recipes.toObject(doc)!!
        }
        loading.value = false

        Timber.d("RecipesViewModel: itemsFlow: items: $rList")

        emit(rList)
    }

    init {
        Timber.d("RecipesViewModel: init")
    }
}