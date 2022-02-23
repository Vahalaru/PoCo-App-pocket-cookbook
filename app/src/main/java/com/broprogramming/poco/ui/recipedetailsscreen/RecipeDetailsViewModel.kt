package com.broprogramming.poco.ui.recipedetailsscreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.broprogramming.poco.model.Recipe
import com.broprogramming.poco.repository.RecipeRepository
import com.broprogramming.poco.ui.destinations.RecipeDetailsUIDestination
import com.google.firebase.firestore.FirebaseFirestore
import com.jet.firestore.getObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    private val repo: RecipeRepository,
    savedStateHandle: SavedStateHandle

) : ViewModel() {

    private val recId = RecipeDetailsUIDestination.argsFrom(savedStateHandle).recipeId

    private val firebaseFirestore = FirebaseFirestore.getInstance()
    private val recipeCollection = firebaseFirestore.collection("cookbook")
    private val recipeDocument = recipeCollection.document(recId)

    internal val recipeDetailsId = recId

    var loading = mutableStateOf(false)

    val recipe = flow {
        loading.value = true
        //val recipe = documentStateOf(recipeDocument)
        var recipe = recipeDocument.get().await().getObject<Recipe>()
        loading.value = false
        Timber.d("Recipe: $recipe")

        emit(recipe)
    }

    init {
        Timber.d("RecipeDetailsViewModel init")
    }

}