package com.example.camera_monitoring.domain.models

data class DoorModel(
    val id: Long,
    val favorites: Boolean,
    val name: String,
    val room: String,
    val image: String
)
