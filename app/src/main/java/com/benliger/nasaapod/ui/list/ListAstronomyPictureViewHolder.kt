package com.benliger.nasaapod.ui.list

import com.benliger.nasaapod.R
import com.benliger.nasaapod.databinding.ListAstronomyPictureFragmentBinding
import com.benliger.nasaapod.ui.list.data.ListAstronomyPictureUiData
import com.benliger.nasaapod.util.ScreenUiData
import com.benliger.nasaapod.util.State
import com.google.android.material.snackbar.Snackbar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ListAstronomyPictureViewHolder(
    private val binding: ListAstronomyPictureFragmentBinding,
    private val viewModel: ListAstronomyPictureViewModel,
) {

    private val compositeDisposable = CompositeDisposable()
    private val adapter = AstronomyPictureAdapter()

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