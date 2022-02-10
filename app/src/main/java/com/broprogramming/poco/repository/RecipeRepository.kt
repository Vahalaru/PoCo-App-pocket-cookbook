package com.broprogramming.poco.repository

import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeRepository @Inject constructor(
    internal val firebaseFirestore: FirebaseFirestore
){
}