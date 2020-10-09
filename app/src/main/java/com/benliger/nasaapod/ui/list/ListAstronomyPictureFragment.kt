package com.benliger.nasaapod.ui.list

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.benliger.nasaapod.R
import com.benliger.nasaapod.databinding.ListAstronomyPictureFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

class ListAstronomyPictureFragment : Fragment() {

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
        viewHolder = ListAstronomyPictureViewHolder(binding, viewModel) { date, title ->
            val directions = ListAstronomyPictureFragmentDirections
                .actionListAstronomyPictureFragmentToDetailAstronomyPictureFragment(date, title)
            findNavController().navigate(directions)
        }
        setHasOptionsMenu(true)
        initializeToolbar()
    }

    private fun initializeToolbar() {
        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(binding.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_picture_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.changeListDisplay -> {
                viewModel.switchListDisplay()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onDestroyView() {
        viewHolder.release()
        _binding = null
        super.onDestroyView()
    }

}