package com.broprogramming.poco.ui.cookbookscreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.broprogramming.poco.model.Recipes
import com.broprogramming.poco.utility.Constants
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(

    //private val queryRecipesByName: Query,
) : ViewModel() {

    var loading = mutableStateOf(false)

    val itemsFlow = flow {
        loading.value = true
        val rList = FirebaseFirestore.getInstance().collection(Constants.RECIPES_COLLECTION)
            .orderBy(Constants.NAME_PROPERTY, Query.Direction.ASCENDING).get().await().map { doc ->
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