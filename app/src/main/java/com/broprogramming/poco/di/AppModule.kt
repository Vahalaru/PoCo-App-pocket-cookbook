package com.broprogramming.poco.di

import com.broprogramming.poco.utility.Constants.NAME_PROPERTY
import com.broprogramming.poco.utility.Constants.RECIPES_COLLECTION
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query.Direction.ASCENDING
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideQueryRecipesByName() = FirebaseFirestore.getInstance().collection(RECIPES_COLLECTION)
        .orderBy(NAME_PROPERTY, ASCENDING)
    @Provides
    @Singleton
    fun provideFirebaseFirestore() = FirebaseFirestore.getInstance()
}