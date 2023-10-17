package com.example.camera_monitoring.presentation.ui.fragment.cameras

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.camera_monitoring.R
import com.example.camera_monitoring.data.utils.Constants.NOT_FOUND
import com.example.camera_monitoring.databinding.FragmentCamerasBinding
import com.example.camera_monitoring.presentation.ui.utils.UiState
import com.example.camera_monitoring.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CamerasFragment :
    BaseFragment<FragmentCamerasBinding, CamerasViewModel>(R.layout.fragment_cameras) {

    override val viewModel: CamerasViewModel by viewModels()
    private val adapter = CamerasAdapter()

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentCamerasBinding.inflate(inflater, container, false)

    override fun initView() {
        super.initView()
        binding.recyclerView.adapter = adapter
        viewModel.getAllCameras()
    }

    override fun initLiveData() {
        super.initLiveData()
        lifecycleScope.launch {
            viewModel.camerasList.collect { result ->
                when (result) {
                    is UiState.Loading -> binding.progressBar.visibility = View.VISIBLE

                    is UiState.Success -> {
                        adapter.setList(result.data!!)
                        binding.progressBar.visibility = View.GONE
                    }

                    is UiState.Empty -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), NOT_FOUND, Toast.LENGTH_SHORT).show()
                    }

                    is UiState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun initClick() {
        super.initClick()
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshCameras()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }
}