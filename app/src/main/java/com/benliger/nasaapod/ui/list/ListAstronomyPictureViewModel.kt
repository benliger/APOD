package com.benliger.nasaapod.ui.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.benliger.nasaapod.R
import com.benliger.nasaapod.service.manager.ApodManager
import com.benliger.nasaapod.ui.common.ScreenUiData
import com.benliger.nasaapod.ui.common.State
import com.benliger.nasaapod.ui.list.data.ListAstronomyPictureUiData
import com.benliger.nasaapod.ui.list.data.LoadingItem
import com.benliger.nasaapod.ui.list.data.mapper.ListAstronomyPictureUiDataMapper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class ListAstronomyPictureViewModel(
    private val context: Application,
    private val apodManager: ApodManager,
) : AndroidViewModel(context) {

    val uiDataSource: BehaviorProcessor<ScreenUiData<ListAstronomyPictureUiData>> by lazy {
        BehaviorProcessor.createDefault(
            ScreenUiData(
                state = State.LOADING,
                data = ListAstronomyPictureUiData(listOf(LoadingItem))
            )
        )
    }

    private val mapper: ListAstronomyPictureUiDataMapper by lazy { ListAstronomyPictureUiDataMapper() }
    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    init {
        fetchData()
    }

    fun fetchData() {
        uiDataSource.value?.let { screenUiData ->
            uiDataSource.onNext(
                screenUiData.copy(
                    state = State.LOADING, data = ListAstronomyPictureUiData(
                        listOf(LoadingItem)
                    )
                )
            )
            compositeDisposable.add(
                apodManager.getLastApodList(NUMBER_APOD_TO_DISPLAY)
                    .subscribeOn(Schedulers.io())
                    .map {
                        mapper.mapToUiData(it)
                    }
                    .subscribe(
                        { data ->
                            uiDataSource.onNext(screenUiData.copy(state = State.IDLE, data = data))
                        },
                        { error ->
                            Timber.e(
                                error,
                                "Error while getting the last $NUMBER_APOD_TO_DISPLAY APODs"
                            )
                            uiDataSource.onNext(
                                screenUiData.copy(
                                    state = State.ERROR,
                                    data = ListAstronomyPictureUiData(),
                                    error = context.getString(R.string.error_fetching_data)
                                )
                            )
                        }
                    )
            )
        }
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    companion object {
        private const val NUMBER_APOD_TO_DISPLAY: Int = 30
    }
}