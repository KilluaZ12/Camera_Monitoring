package com.example.camera_monitoring.data.repository

import com.example.camera_monitoring.data.dto.toDataDto
import com.example.camera_monitoring.data.dto.toDomainModel
import com.example.camera_monitoring.data.local.db.DoorDao
import com.example.camera_monitoring.data.remoute.HomeApiService
import com.example.camera_monitoring.data.utils.GetResource
import com.example.camera_monitoring.domain.models.DoorModel
import com.example.camera_monitoring.domain.repository.DoorRepository
import com.example.camera_monitoring.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DoorRepositoryImpl @Inject constructor(
    private val dao: DoorDao,
    private val houseApiService: HomeApiService,
) : DoorRepository, GetResource() {
    override suspend fun getRemoteDoors() = getResult {
        houseApiService.getDoor().body()!!.data.toDomainModel()
    }

    override fun getLocalDoors(): List<DoorModel> {
        return dao.getAllDoors().toDomainModel()
    }

    override fun insertDoor(doorModel: DoorModel) {
        dao.insertDoor(doorModel.toDataDto())
    }


    override fun insertLocalDoors(doorModels: List<DoorModel>) {
        dao.insertAllDoors(doorModels.toDataDto())
    }

    override fun updateDoor(doorModel: DoorModel) {
        dao.updateDoor(doorModel.toDataDto())
    }

    override fun updateLocalDoors(doorModels: List<DoorModel>) {
        dao.updateAllDoors(doorModels.toDataDto())
    }

    override fun deleteDoor(doorModel: DoorModel) {
        dao.deleteDoor(doorModel.toDataDto())
    }

}