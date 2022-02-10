package com.broprogramming.poco.repository.model

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.toObject

data class Recipes(
    var recipe_description: String = "",
    var recipe_name: String = "",
    var recipe_details_id: DocumentReference? = null,
    var category:String? = null,
    var id: String? = null
) {
    companion object{
        fun toObject(doc: DocumentSnapshot): Recipes?{
            val item = doc.toObject<Recipes>()
            item?.id = doc.id
            return item
        }
    }
}