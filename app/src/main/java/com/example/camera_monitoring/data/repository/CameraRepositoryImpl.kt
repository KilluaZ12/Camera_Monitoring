package com.example.camera_monitoring.data.repository

import com.example.camera_monitoring.data.dto.toDataDto
import com.example.camera_monitoring.data.dto.toDomainModel
import com.example.camera_monitoring.data.local.db.CameraDao
import com.example.camera_monitoring.data.remoute.HomeApiService
import com.example.camera_monitoring.data.utils.GetResource
import com.example.camera_monitoring.domain.models.CameraModel
import com.example.camera_monitoring.domain.repository.CameraRepository
import com.example.camera_monitoring.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CameraRepositoryImpl @Inject constructor(
    private val dao: CameraDao,
    private val houseApiService: HomeApiService,
) : CameraRepository, GetResource() {
    override suspend fun getRemoteCameras() = getResult {
        houseApiService.getCamera().body()!!.data.cameras.toDomainModel()
    }

    override fun getLocalCameras(): List<CameraModel> {
        return dao.getAllCameras().toDomainModel()
    }

    override fun insertCamera(cameraModel: CameraModel) {
        dao.insertCamera(cameraModel.toDataDto())
    }

    override fun insertLocalCameras(cameraModels: List<CameraModel>) {
        dao.insertAllCameras(cameraModels.toDataDto())
    }

    override fun updateCamera(cameraModel: CameraModel) {
        dao.updateCamera(cameraModel.toDataDto())
    }

    override fun updateLocalCameras(cameraModels: List<CameraModel>) {
        dao.updateAllCameras(cameraModels.toDataDto())
    }

    override fun deleteCamera(cameraModel: CameraModel) {
        dao.deleteCamera(cameraModel.toDataDto())
    }
}