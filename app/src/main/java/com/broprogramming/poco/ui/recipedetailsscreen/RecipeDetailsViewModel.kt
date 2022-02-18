package com.broprogramming.poco.ui.recipedetailsscreen

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.broprogramming.poco.model.Recipe
import com.broprogramming.poco.model.RecipeDetailsScreenNavArgs
import com.broprogramming.poco.ui.destinations.RecipeDetailsUIDestination.argsFrom
import com.google.firebase.firestore.FirebaseFirestore
import com.jet.firestore.getObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
//    private val repo: RecipeRepository,

) : ViewModel() {

    val recId = RecipeDetailsScreenNavArgs().recipeId
    val test2 = argsFrom(savedStateHandle = rememberSaveableStateHolder())


    private val firebaseFirestore = FirebaseFirestore.getInstance()
    private val recipeCollection = firebaseFirestore.collection("cookbook")



    var loading = mutableStateOf(false)

    val recipe = flow {
        loading.value = true
        val recipe = recipeCollection.document(argsFrom(savedStateHandle = SavedStateHandle()).recipeId.toString()).get().await().getObject<Recipe>()//getRecipe(recipeDetailsScreenNavArgs.recipeId).await()
        loading.value = false
        Timber.d("Recipe: $recipe")

        emit(recipe)
    }

    init {
        Timber.d("RecipeDetailsViewModel init")
    }
    private fun getRecipe(rId: String): String {
        return rId
    }

}