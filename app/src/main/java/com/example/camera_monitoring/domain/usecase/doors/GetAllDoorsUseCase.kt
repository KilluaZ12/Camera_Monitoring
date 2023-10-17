package com.example.camera_monitoring.domain.usecase.doors

import com.example.camera_monitoring.domain.models.DoorModel
import com.example.camera_monitoring.domain.repository.DoorRepository
import com.example.camera_monitoring.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllDoorsUseCase @Inject constructor(
    private val doorRepository: DoorRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<DoorModel>>> {
        val data = doorRepository.getLocalDoors()
        if (data.isEmpty()) {
            doorRepository.getRemoteDoors().collect {
                if (it is Resource.Success) {
                    doorRepository.insertLocalDoors(it.data!!)
                }
            }
            return doorRepository.getRemoteDoors()
        } else {
            return flow {
                emit(Resource.Success(data = data))
            }
        }
    }
}