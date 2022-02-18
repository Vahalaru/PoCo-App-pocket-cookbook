package com.broprogramming.poco.repository

import com.broprogramming.poco.model.DataOrException
import com.broprogramming.poco.model.Recipes
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipesRepository @Inject constructor(
    internal val queryRecipesByName: Query,
){
    suspend fun getRecipesFromFirestore(): DataOrException<List<Recipes>, Exception> {
        val dataOrException = DataOrException<List<Recipes>, Exception>()
        try {
            dataOrException.data = queryRecipesByName.get().await().map { document ->
                Recipes.toObject(document)!!

            }
            Timber.d("getRecipesFromFirestore: ${dataOrException.data}")
        } catch (e: FirebaseFirestoreException) {
            dataOrException.e = e
        }
        return dataOrException
    }
}