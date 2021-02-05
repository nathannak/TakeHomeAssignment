package com.demo.demoapplication.repository

import com.demo.demoapplication.model.Store


/**
class that holds a value with its loading status.
Written by NN
 */

data class Resource(val status: Status, val data: List<Store>?, val message: String?) {

    companion object {
        fun success(data: List<Store>): Resource {
            return Resource(Status.SUCCESS, data, null)
        }

        fun error(msg: String, data: List<Store>?): Resource {
            return Resource(Status.ERROR, null, msg)
        }

        fun loading(): Resource {
            return Resource(Status.LOADING, null, null)
        }
    }

}