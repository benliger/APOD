package com.benliger.nasaapod.util

data class ScreenUiData<T>(val state: State = State.IDLE, val data: T, val error: String? = null)

enum class State {

    /**
     * State loading stands for the loading when the screen is beginning
     */
    LOADING,

    /**
     * State idle stands for the screen is not being doing anything
     */
    IDLE,

    /**
     * State error stands for the screen is in error
     */
    ERROR,

    /**
     * State empty stands for the screen has no data to display
     */
    EMPTY
}