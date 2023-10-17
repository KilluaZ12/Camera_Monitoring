package com.example.camera_monitoring.domain.usecase.doors

import com.example.camera_monitoring.domain.models.DoorModel
import com.example.camera_monitoring.domain.repository.DoorRepository
import javax.inject.Inject

class UpdateDoorUseCase @Inject constructor(
    private val doorRepository: DoorRepository
) {
    fun updateDoor(doorModel: DoorModel) = doorRepository.updateDoor(doorModel)
}