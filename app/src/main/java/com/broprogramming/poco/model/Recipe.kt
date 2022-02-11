package com.broprogramming.poco.model

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.toObject

data class Recipe(
    var recipeCategory: String = "",
    var recipeId: String = "",
    var recipeName: String = "",
    var recipeDescription: String = "",
    var recipeIngredients: List<Ingredient>? = null,
    var recipeSteps: List<Step>? = null,
    var id: String = "",
){
    companion object{
        fun toObject(doc: DocumentSnapshot): Recipe?{
            val item = doc.toObject<Recipe>()
            item?.id = doc.id
            return item
        }
    }
}
