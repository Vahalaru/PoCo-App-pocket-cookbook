package com.broprogramming.poco.model

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.toObject

data class Recipe(
    var category: String = "",
    var recipe_name: String = "",
    var description: String = "",
    var recipe_added_by: String = "",
    var ingredients: List<Ingredient>? = null,
    var recipe_steps: List<Step>? = null,
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
