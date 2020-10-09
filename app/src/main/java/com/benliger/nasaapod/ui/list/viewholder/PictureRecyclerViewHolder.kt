package com.benliger.nasaapod.ui.list.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.benliger.nasaapod.R
import com.benliger.nasaapod.databinding.AstronomyPictureRecyclerViewHolderBinding
import com.benliger.nasaapod.ui.list.data.PictureItem
import com.benliger.nasaapod.util.loadImage

class PictureRecyclerViewHolder(
    private val binding: AstronomyPictureRecyclerViewHolderBinding,
    private val pictureClickAction: (date: String, title: String) -> Unit,
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: PictureItem) {
        binding.root.setOnClickListener {
            pictureClickAction(item.date, item.name)
        }
        binding.picture.loadImage(item.picture, R.drawable.ic_twotone_scatter_plot)
        binding.label.text = item.name
    }

}
