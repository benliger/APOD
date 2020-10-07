package com.benliger.nasaapod.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.benliger.nasaapod.R

class ListAstronomyPictureFragment : Fragment() {

    companion object {
        fun newInstance() = ListAstronomyPictureFragment()
    }

    private lateinit var viewModel: ListAstronomyPictureViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.list_astronomy_picture_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListAstronomyPictureViewModel::class.java)
    }

}