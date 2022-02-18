package com.broprogramming.poco.repository

import com.google.firebase.firestore.CollectionReference
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeRepository @Inject constructor(
    internal val queryRecipeById: CollectionReference
){
}