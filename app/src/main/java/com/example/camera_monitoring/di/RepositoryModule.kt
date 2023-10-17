package com.example.camera_monitoring.di

import com.example.camera_monitoring.data.repository.CameraRepositoryImpl
import com.example.camera_monitoring.data.repository.DoorRepositoryImpl
import com.example.camera_monitoring.domain.repository.CameraRepository
import com.example.camera_monitoring.domain.repository.DoorRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindCameraRepository(cameraRepositoryImpl: CameraRepositoryImpl): CameraRepository

    @Binds
    fun bindDoorRepository(doorRepositoryImpl: DoorRepositoryImpl): DoorRepository
}