package com.broprogramming.poco.model

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.toObject

data class Menu (val chosen_menu: List<String>, val menu_size: Int, val menu_users: List<String>, var id: String){
    companion object{
        fun toObject(doc: DocumentSnapshot): Menu?{
            val item = doc.toObject<Menu>()
            item?.id = doc.id
            return item
        }
    }
}