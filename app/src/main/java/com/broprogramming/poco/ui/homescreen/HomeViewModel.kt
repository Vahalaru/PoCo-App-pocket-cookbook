package com.broprogramming.poco.ui.homescreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.broprogramming.poco.model.Menu
import com.broprogramming.poco.utility.Constants
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    //private val repo: HomeRepository
): ViewModel() {
    private var loading = mutableStateOf(false)

    val menuItemsFlow = flow {
        loading.value = true
        val rList = FirebaseFirestore.getInstance().collection(Constants.MENU_COLLECTION)
            .whereIn(Constants.MENU_USERS_PROPERTY, listOf("y2a5MH011adTMqf4d4UR")).get().await().map { doc ->
            Menu.toObject(doc)!!.chosen_menu
        }
        loading.value = false
        Timber.d("HomeViewModel: itemsFlow: items: $rList")

        emit(rList)
    }

    init {
        Timber.d("HomeViewModel: init")
    }
}