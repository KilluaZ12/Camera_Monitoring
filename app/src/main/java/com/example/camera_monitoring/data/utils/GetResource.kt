package com.example.camera_monitoring.data.utils

import com.example.camera_monitoring.data.utils.Constants.CONNECTION_ERROR
import com.example.camera_monitoring.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class GetResource {
    protected suspend fun <T> getResult(result: suspend () -> T) = flow {
        emit(Resource.Loading())
        try {
            val data = result()
            emit(Resource.Success(data))
        } catch (exception: Exception) {
            emit(Resource.Error(message = exception.message ?: CONNECTION_ERROR))
        }
    }.flowOn(Dispatchers.IO)
}