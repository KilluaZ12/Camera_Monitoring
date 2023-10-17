package com.example.camera_monitoring.domain.usecase.doors

import com.example.camera_monitoring.domain.models.DoorModel
import com.example.camera_monitoring.domain.repository.DoorRepository
import javax.inject.Inject

class InsertDoorUseCase @Inject constructor(
    private val doorRepository: DoorRepository
) {
    fun insertDoor(doorModel: DoorModel) = doorRepository.insertDoor(doorModel)
}