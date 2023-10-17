package com.example.camera_monitoring.presentation.ui.fragment.cameras

import androidx.lifecycle.viewModelScope
import com.example.camera_monitoring.domain.models.CameraModel
import com.example.camera_monitoring.domain.usecase.cameras.DeleteCameraUseCase
import com.example.camera_monitoring.domain.usecase.cameras.GetAllCamerasUseCase
import com.example.camera_monitoring.domain.usecase.cameras.InsertCameraUseCase
import com.example.camera_monitoring.domain.usecase.cameras.RefreshCamerasUseCase
import com.example.camera_monitoring.domain.usecase.cameras.UpdateCameraUseCase
import com.example.camera_monitoring.domain.utils.Resource
import com.example.camera_monitoring.presentation.ui.utils.UiState
import com.example.camera_monitoring.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CamerasViewModel @Inject constructor(
    private val getAllCamerasUseCase: GetAllCamerasUseCase,
    private val refreshCamerasUseCase: RefreshCamerasUseCase,
    private val insertCameraUseCase: InsertCameraUseCase,
    private val updateCameraUseCase: UpdateCameraUseCase,
    private val deleteCameraUseCase: DeleteCameraUseCase
) : BaseViewModel() {

    private val _camerasList = MutableStateFlow<UiState<List<CameraModel>>>(UiState.Loading())
    val camerasList: StateFlow<UiState<List<CameraModel>>> = _camerasList

    fun getAllCameras() = doRequest {
        getAllCamerasUseCase()
    }

    fun refreshCameras() = doRequest {
        refreshCamerasUseCase()
    }

    private fun doRequest(useCase: suspend () -> Flow<Resource<List<CameraModel>>>) {
        viewModelScope.launch(Dispatchers.IO) {
            _camerasList.value = collectData { useCase() }
        }
    }
}