package com.benliger.nasaapod.ui.list

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.benliger.nasaapod.R
import com.benliger.nasaapod.databinding.ListAstronomyPictureFragmentBinding
import com.benliger.nasaapod.ui.common.ScreenUiData
import com.benliger.nasaapod.ui.common.State
import com.benliger.nasaapod.ui.list.data.ListAstronomyPictureUiData
import com.benliger.nasaapod.ui.list.data.ListDisplay
import com.google.android.material.snackbar.Snackbar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ListAstronomyPictureViewHolder(
    private val binding: ListAstronomyPictureFragmentBinding,
    private val viewModel: ListAstronomyPictureViewModel,
    pictureClickAction: (date: String, title: String) -> Unit,
) {

    private val compositeDisposable = CompositeDisposable()
    private val adapter = AstronomyPictureAdapter(pictureClickAction)

    init {
        binding.recycler.adapter = adapter
        compositeDisposable.add(viewModel.uiDataSource.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { screenUiData ->
                updateUiDataForChange(screenUiData)
            })

    }

    private fun updateUiDataForChange(screenUiData: ScreenUiData<ListAstronomyPictureUiData>) {
        adapter.submitList(screenUiData.data.uiRecyclerItem)

        val currentLayoutManager = binding.recycler.layoutManager
        val context = binding.root.context
        when (screenUiData.data.display) {
            ListDisplay.VERTICAL -> {
                if (currentLayoutManager is GridLayoutManager) {
                    binding.recycler.layoutManager = LinearLayoutManager(context)
                }
            }
            ListDisplay.GRID -> {
                if (currentLayoutManager !is GridLayoutManager) {
                    binding.recycler.layoutManager = GridLayoutManager(
                        context,
                        context.resources.getInteger(R.integer.gridImageCount)
                    )
                }
            }
        }

        if (screenUiData.state == State.ERROR && !screenUiData.error.isNullOrEmpty()) {
            Snackbar.make(binding.root, screenUiData.error, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.retry) {
                    viewModel.fetchData()
                }.show()
        }
    }

    fun release() {
        compositeDisposable.clear()
    }

}