package com.example.camera_monitoring.domain.usecase.doors

import com.example.camera_monitoring.domain.models.DoorModel
import com.example.camera_monitoring.domain.repository.DoorRepository
import com.example.camera_monitoring.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RefreshDoorsUseCase @Inject constructor(
    private val doorRepository: DoorRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<DoorModel>>> {

        doorRepository.getRemoteDoors().collect { resource ->
            if (resource is Resource.Success) {
                if (doorRepository.getLocalDoors().isEmpty()) {
                    doorRepository.insertLocalDoors(resource.data!!)
                } else {
                    doorRepository.updateLocalDoors(resource.data!!)
                }
            }
        }
        return doorRepository.getRemoteDoors()
    }
}