package com.benliger.nasaapod.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.benliger.nasaapod.R
import com.benliger.nasaapod.service.manager.ApodManager
import com.benliger.nasaapod.service.model.Apod
import com.benliger.nasaapod.util.ScreenUiData
import com.benliger.nasaapod.util.State
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.schedulers.Schedulers
import org.joda.time.LocalDate
import timber.log.Timber

class DetailAstronomyPictureViewModel(
    private val context: Application,
    private val apodManager: ApodManager,
    private val date: String
) : AndroidViewModel(context) {

    val uiDataSource: BehaviorProcessor<ScreenUiData<Apod>> by lazy {
        BehaviorProcessor.createDefault(
            ScreenUiData(
                state = State.LOADING,
                data = Apod()
            )
        )
    }

    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    init {
        fetchData()
    }

    fun fetchData() {
        uiDataSource.value?.let { screenUiData ->
            uiDataSource.onNext(
                screenUiData.copy(
                    state = State.LOADING, data = Apod()
                )
            )
            compositeDisposable.add(
                apodManager.getApod(LocalDate.parse(date), false)
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        { data ->
                            uiDataSource.onNext(screenUiData.copy(state = State.IDLE, data = data))
                        },
                        { error ->
                            Timber.e(
                                error,
                                "Error while getting the $date APOD"
                            )
                            uiDataSource.onNext(
                                screenUiData.copy(
                                    state = State.ERROR,
                                    data = Apod(),
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

}