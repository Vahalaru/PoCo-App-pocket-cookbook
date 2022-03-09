package com.broprogramming.poco.repository

import com.google.firebase.firestore.Query
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipesRepository @Inject constructor(
    private val queryRecipesByName: Query,
){
}