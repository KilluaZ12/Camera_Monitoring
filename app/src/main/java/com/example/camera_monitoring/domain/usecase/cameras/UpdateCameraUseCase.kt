package com.example.camera_monitoring.domain.usecase.cameras

import com.example.camera_monitoring.domain.models.CameraModel
import com.example.camera_monitoring.domain.repository.CameraRepository
import javax.inject.Inject

class UpdateCameraUseCase @Inject constructor(
    private val cameraRepository: CameraRepository
) {
    fun updateCamera(cameraModel: CameraModel) = cameraRepository.updateCamera(cameraModel)
}