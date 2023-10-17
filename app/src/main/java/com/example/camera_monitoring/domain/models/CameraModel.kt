package com.example.camera_monitoring.domain.models

data class CameraModel(
    val id: Long,
    val favorites: Boolean,
    val name: String,
    val rec: Boolean,
    val room: String,
    val image: String
)
