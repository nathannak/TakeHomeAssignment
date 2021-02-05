package com.demo.demoapplication.repository

import android.content.Context
import com.demo.demoapplication.di.RepositoryDependencies
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/*
Repository in charge of making the network call.
Written by NN
*/

// Inject retrofit via constructor

class Repository @Inject constructor(
    private val repository: RepositoryDependencies,
    @ApplicationContext val context: Context
) {

    suspend fun fetchStores(lat: String, lng: String, offset: String, limit: String) =

        flow {

            val response = repository.provideRestaurants().getRestaurants(lat, lng, offset, limit)

            if (response == null) emit(Resource(Status.ERROR, null, "EMPTY RESPONSE"))
            else {
                if (response.isSuccessful) {
                    emit(Resource(Status.SUCCESS, response.body()?.stores, null))
                } else {
                    emit(Resource(Status.ERROR, null, response.message()))
                }
            }
        }.flowOn(Dispatchers.Main)
}

