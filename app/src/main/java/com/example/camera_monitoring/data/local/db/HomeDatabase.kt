package com.example.camera_monitoring.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.camera_monitoring.data.dto.CameraDto
import com.example.camera_monitoring.data.dto.DoorDto

@Database(entities = [CameraDto::class, DoorDto::class], version = 1, exportSchema = true)
abstract class HomeDatabase : RoomDatabase() {
    abstract fun getCameraDao(): CameraDao
    abstract fun getDoorDao(): DoorDao
}