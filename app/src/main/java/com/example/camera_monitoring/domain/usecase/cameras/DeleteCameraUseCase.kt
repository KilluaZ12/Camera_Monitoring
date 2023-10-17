package com.example.camera_monitoring.domain.usecase.cameras

import com.example.camera_monitoring.domain.models.CameraModel
import com.example.camera_monitoring.domain.repository.CameraRepository
import javax.inject.Inject

class DeleteCameraUseCase @Inject constructor(
    private val cameraRepository: CameraRepository
) {
    fun deleteCamera(cameraModel: CameraModel) = cameraRepository.deleteCamera(cameraModel)
}