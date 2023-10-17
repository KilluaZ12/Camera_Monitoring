package com.example.camera_monitoring.data.remoute

import com.example.camera_monitoring.data.dto.CamerasDto
import com.example.camera_monitoring.data.dto.DoorsDto
import retrofit2.Response
import retrofit2.http.GET

interface HomeApiService {

    @GET("cameras/")
    suspend fun getCamera(): Response<CamerasDto>

    @GET("doors/")
    suspend fun getDoor(): Response<DoorsDto>
}