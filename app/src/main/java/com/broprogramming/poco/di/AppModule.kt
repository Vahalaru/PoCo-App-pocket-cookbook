package com.broprogramming.poco.di

import com.broprogramming.poco.utility.Constants.MENU_COLLECTION
import com.broprogramming.poco.utility.Constants.MENU_USERS_PROPERTY
import com.broprogramming.poco.utility.Constants.NAME_PROPERTY
import com.broprogramming.poco.utility.Constants.RECIPES_COLLECTION
import com.broprogramming.poco.utility.Constants.RECIPE_DETAILS
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query.Direction.ASCENDING
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideQueryRecipesByName() = FirebaseFirestore.getInstance().collection(RECIPES_COLLECTION)
        .orderBy(NAME_PROPERTY, ASCENDING)
    //TODO: Change in the whereIn clause to get the recipe by the users ID.
    @Provides
    fun provideQueryMenuByMenuUsers() = FirebaseFirestore.getInstance().collection(MENU_COLLECTION)
        .whereIn(MENU_USERS_PROPERTY, listOf("y2a5MH011adTMqf4d4UR"))
    @Provides
    fun provideQueryRecipeById() = FirebaseFirestore.getInstance().collection(RECIPE_DETAILS)
}