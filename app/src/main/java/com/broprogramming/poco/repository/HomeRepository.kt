package com.broprogramming.poco.repository

import com.broprogramming.poco.model.DataOrException
import com.broprogramming.poco.model.Menu
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepository @Inject constructor(
    internal val queryMenuByMenuUsers: Query,
) {

    suspend fun getMenuFromFirestore(): DataOrException<List<Menu>, Exception> {
        val dataOrException = DataOrException<List<Menu>, Exception>()
        try {
            dataOrException.data = queryMenuByMenuUsers.get().await().map { document ->
                Menu.toObject(document)!!
            }
            Timber.d("getMenuFromFirestore: ${dataOrException.data}")
        } catch (e: FirebaseFirestoreException) {
            dataOrException.e = e
        }
        return dataOrException
    }
}

