package com.broprogramming.poco.ui.recipedetailsscreen

import androidx.lifecycle.ViewModel
import com.broprogramming.poco.repository.RecipeRepository
import com.broprogramming.poco.utility.Constants.RECIPE_DETAILS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    private val repo: RecipeRepository
) : ViewModel() {

    val itemFlow = flow {
        val recipe = repo.firebaseFirestore.collection(RECIPE_DETAILS)//.document(queryRecipeDetailsById).get().await().getObject<Recipe>()
        Timber.d("Recipe: $recipe")
        emit(recipe)
    }

    init {
        Timber.d("RecipeDetailsViewModel init")
    }
}