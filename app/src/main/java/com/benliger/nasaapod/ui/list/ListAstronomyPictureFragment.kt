package com.benliger.nasaapod.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.benliger.nasaapod.databinding.ListAstronomyPictureFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

class ListAstronomyPictureFragment : Fragment() {

    companion object {
        fun newInstance() = ListAstronomyPictureFragment()
    }

    private val viewModel: ListAstronomyPictureViewModel by viewModel()
    private var viewHolder by Delegates.notNull<ListAstronomyPictureViewHolder>()

    private var _binding: ListAstronomyPictureFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ListAstronomyPictureFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewHolder = ListAstronomyPictureViewHolder(binding, viewModel)
    }

    override fun onDestroyView() {
        viewHolder.release()
        _binding = null
        super.onDestroyView()
    }

}