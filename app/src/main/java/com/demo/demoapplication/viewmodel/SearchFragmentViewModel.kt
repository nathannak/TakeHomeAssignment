package com.demo.demoapplication.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.demoapplication.model.Store
import com.demo.demoapplication.repository.Repository
import com.demo.demoapplication.repository.Status
import com.demo.demoapplication.util.Constants
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/*
ViewModel class for SearchFragment
Written by NN
*/

class SearchFragmentViewModel @ViewModelInject constructor(

    //Inject repository to ViewModel using Hilt
    private val repository: Repository,
) : ViewModel() {

    var liveData: MutableLiveData<List<Store>> = MutableLiveData()
    var loading = MutableLiveData<Boolean>().apply { postValue(true) }
    var error = MutableLiveData<Boolean>().apply { postValue(false) }

    fun getStoresFromRepository() {
        viewModelScope.launch {
            repository.fetchStores(Constants.lat, Constants.lng, Constants.offset, Constants.limit)
                .collect {
                    if (it.status == Status.SUCCESS) {
                        liveData.postValue(it.data)
                        loading.postValue(false)
                    } else if (it.status == Status.LOADING) {
                        error.postValue(true)
                    }
                }
        }
    }
}