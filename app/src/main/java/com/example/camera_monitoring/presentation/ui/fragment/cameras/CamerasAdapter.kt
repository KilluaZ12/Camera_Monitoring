package com.example.camera_monitoring.presentation.ui.fragment.cameras

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.camera_monitoring.databinding.ItemCameraBinding
import com.example.camera_monitoring.domain.models.CameraModel

class CamerasAdapter(
) : RecyclerView.Adapter<CamerasAdapter.CamerasViewHolder>() {

    private var list: List<CameraModel> = listOf()

    fun setList(newList: List<CameraModel>) {
        list = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CamerasViewHolder {
        return CamerasViewHolder(
            ItemCameraBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CamerasViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class CamerasViewHolder(private val binding: ItemCameraBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(camera: CameraModel) {
            with(binding) {
                ivImage.load(camera.image)
                tvTitle.text = camera.name
                if (camera.rec) {
                    icRec.visibility = View.VISIBLE
                } else {
                    icRec.visibility = View.GONE
                }
                if (camera.favorites) {
                    icStar.visibility = View.VISIBLE
                } else {
                    icStar.visibility = View.GONE
                }
            }
            itemView.setOnClickListener {
            }
        }
    }
}