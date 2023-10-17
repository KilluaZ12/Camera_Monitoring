package com.example.camera_monitoring.presentation.ui.fragment.doors

import androidx.lifecycle.viewModelScope
import com.example.camera_monitoring.domain.models.DoorModel
import com.example.camera_monitoring.domain.usecase.doors.DeleteDoorUseCase
import com.example.camera_monitoring.domain.usecase.doors.GetAllDoorsUseCase
import com.example.camera_monitoring.domain.usecase.doors.InsertDoorUseCase
import com.example.camera_monitoring.domain.usecase.doors.RefreshDoorsUseCase
import com.example.camera_monitoring.domain.usecase.doors.UpdateDoorUseCase
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
class DoorsViewModel @Inject constructor(
    private val getAllDoorsUseCase: GetAllDoorsUseCase,
    private val refreshDoorsUseCase: RefreshDoorsUseCase,
    private val insertDoorUseCase: InsertDoorUseCase,
    private val updateDoorUseCase: UpdateDoorUseCase,
    private val deleteDoorUseCase: DeleteDoorUseCase
) : BaseViewModel() {

    private val _doorsList = MutableStateFlow<UiState<List<DoorModel>>>(UiState.Loading())
    val doorsList: StateFlow<UiState<List<DoorModel>>> = _doorsList

    fun getAllDoors() = doRequest {
        getAllDoorsUseCase()
    }

    fun refreshDoors() = doRequest {
        refreshDoorsUseCase()
    }

    private fun doRequest(useCase: suspend () -> Flow<Resource<List<DoorModel>>>) {
        viewModelScope.launch(Dispatchers.IO) {
            _doorsList.value = collectData { useCase() }
        }
    }
}