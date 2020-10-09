package com.benliger.nasaapod.ui.detail

import androidx.core.view.isVisible
import com.benliger.nasaapod.R
import com.benliger.nasaapod.databinding.DetailAstronomyPictureFragmentBinding
import com.benliger.nasaapod.service.model.Apod
import com.benliger.nasaapod.util.ScreenUiData
import com.benliger.nasaapod.util.State
import com.benliger.nasaapod.util.loadImage
import com.google.android.material.snackbar.Snackbar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class DetailAstronomyPictureViewHolder(
    private val binding: DetailAstronomyPictureFragmentBinding,
    private val viewModel: DetailAstronomyPictureViewModel,
) {

    private val compositeDisposable = CompositeDisposable()

    init {
        compositeDisposable.add(viewModel.uiDataSource.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { screenUiData ->
                updateUiDataForChange(screenUiData)
            })
    }

    private fun updateUiDataForChange(screenUiData: ScreenUiData<Apod>) {
        when (screenUiData.state) {
            State.LOADING -> {
                binding.progress.isVisible = true
                binding.content.isVisible = false
            }
            State.IDLE -> {
                binding.progress.isVisible = false
                binding.content.isVisible = true

                binding.description.text = screenUiData.data.explanation
                binding.copyright.text = screenUiData.data.copyright
                binding.picture.loadImage(screenUiData.data.url, R.drawable.ic_twotone_scatter_plot)
            }
            State.ERROR -> {
                binding.progress.isVisible = false
                binding.content.isVisible = false
                if (!screenUiData.error.isNullOrEmpty()) {
                    Snackbar.make(binding.root, screenUiData.error, Snackbar.LENGTH_INDEFINITE)
                        .setAction(R.string.retry) {
                            viewModel.fetchData()
                        }.show()
                }
            }
            State.EMPTY -> {
                Timber.w("empty state screen is not supposed to happen for detail screen")
            }
        }
    }

    fun release() {
        compositeDisposable.clear()
    }

}