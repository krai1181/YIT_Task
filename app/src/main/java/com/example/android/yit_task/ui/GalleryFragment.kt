package com.example.android.yit_task.ui

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.ExperimentalPagingApi
import com.example.android.yit_task.databinding.GalleryFragmentBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*


const val BUNDLE_KEY = "q_key"
const val REQUEST_KEY = "requestKey"

class GalleryFragment : Fragment() {

    private var searchJob: Job? = null
    private lateinit var viewModel: GalleryViewModel
    private lateinit var binding: GalleryFragmentBinding
    private lateinit var preferences: SharedPreferences
    private lateinit var adapter: ImagesAdapter

    private val sharedPrefFile = "com.example.android.yit_task.ui"
    private val appPrefSearch = "search"
    private val appPrefPage = "page"
    private val baseQ = "kitten"

    private var lastSearch: String = baseQ
    private var currentPage = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = GalleryFragmentBinding.inflate(inflater)
        viewModel = ViewModelProvider(this)[GalleryViewModel::class.java]
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        preferences = requireActivity().getSharedPreferences(sharedPrefFile, MODE_PRIVATE)


        initAdapter()

        sendDataToDetailFragment()


        navigateToDetailScreen()

        updateItemListFromInput()

        return binding.root
    }

    private fun sendDataToDetailFragment() {
        viewModel.properties.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                setFragmentResult(REQUEST_KEY, bundleOf(BUNDLE_KEY to it))
            }
        })
    }

    private fun navigateToDetailScreen() {
        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                this.findNavController()
                    .navigate(GalleryFragmentDirections.actionGalleryFragment2ToDetailFragment2(it))
                viewModel.completeDisplayingProperty()
            }
        })
    }

    private fun updateItemListFromInput() {
        binding.btnSearch.setOnClickListener {
            val q = binding.editTv.text.toString().toLowerCase(Locale.getDefault())
            binding.rvImages.scrollToPosition(0)
            search(q)
            lastSearch = q
        }
    }


    private fun search(query: String) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            @OptIn(ExperimentalCoroutinesApi::class)
            viewModel.updateSearch(query).collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }

    private fun initAdapter() {
        adapter = ImagesAdapter(ImagesAdapter.OnClickListener {
            viewModel.displayPropertyDetail(it)
        })
        binding.rvImages.adapter = adapter


    }

    override fun onPause() {
        super.onPause()
        val editor = preferences.edit()
        editor.putString(appPrefSearch, lastSearch)
        editor.putInt(appPrefPage, currentPage)
        editor.apply()
    }

    override fun onResume() {
        super.onResume()
        if (preferences.contains(appPrefSearch)) {
            lastSearch = preferences.getString(appPrefSearch, baseQ).toString()
            currentPage = preferences.getInt(appPrefPage, 1)
            search(lastSearch)
            lifecycleScope.launch {
                @OptIn(ExperimentalPagingApi::class)
                adapter.dataRefreshFlow.collect {
                    binding.rvImages.scrollToPosition(0)
                }
            }
        }
    }


}
