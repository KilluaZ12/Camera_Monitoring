package com.example.camera_monitoring.domain.usecase.cameras

import com.example.camera_monitoring.domain.models.CameraModel
import com.example.camera_monitoring.domain.repository.CameraRepository
import com.example.camera_monitoring.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RefreshCamerasUseCase @Inject constructor(
    private val cameraRepository: CameraRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<CameraModel>>> {

        cameraRepository.getRemoteCameras().collect { resource ->
            if (resource is Resource.Success) {
                if (cameraRepository.getLocalCameras().isEmpty()) {
                    cameraRepository.insertLocalCameras(resource.data!!)
                } else {
                    cameraRepository.updateLocalCameras(resource.data!!)
                }
            }
        }
        return cameraRepository.getRemoteCameras()
    }
}