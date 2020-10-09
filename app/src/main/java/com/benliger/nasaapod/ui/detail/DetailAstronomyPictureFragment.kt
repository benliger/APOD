package com.benliger.nasaapod.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.benliger.nasaapod.databinding.DetailAstronomyPictureFragmentBinding
import kotlinx.android.synthetic.main.detail_astronomy_picture_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import kotlin.properties.Delegates

class DetailAstronomyPictureFragment : Fragment() {

    private val args by navArgs<DetailAstronomyPictureFragmentArgs>()
    private val viewModel: DetailAstronomyPictureViewModel by viewModel() {
        parametersOf(args.date)
    }
    private var viewHolder by Delegates.notNull<DetailAstronomyPictureViewHolder>()

    private var _binding: DetailAstronomyPictureFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailAstronomyPictureFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewHolder =
            DetailAstronomyPictureViewHolder(binding, viewModel) { pictureUrl, pictureUrlHd ->
                val directions = DetailAstronomyPictureFragmentDirections
                    .actionDetailAstronomyPictureFragmentToAstronomyPictureFragment(
                        pictureUrl,
                        pictureUrlHd
                    )
                findNavController().navigate(directions)
            }
        initializeToolbar()
    }

    private fun initializeToolbar() {
        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(toolbar)
        toolbar.title = args.title
        toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onDestroyView() {
        viewHolder.release()
        _binding = null
        super.onDestroyView()
    }

}