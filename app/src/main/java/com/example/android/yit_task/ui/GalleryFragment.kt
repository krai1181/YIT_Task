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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.yit_task.databinding.GalleryFragmentBinding
import java.util.*


const val BUNDLE_KEY = "q_key"
const val REQUEST_KEY = "requestKey"

class GalleryFragment : Fragment() {

    private lateinit var viewModel: GalleryViewModel
    private lateinit var binding: GalleryFragmentBinding
    private lateinit var preferences: SharedPreferences
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



        binding.rvImages.adapter = ImagesAdapter(ImagesAdapter.OnClickListener {
            viewModel.displayPropertyDetail(it.hit)
        })

        val layoutManager = GridLayoutManager(context, 10)
        layoutManager.spanSizeLookup  = (binding.rvImages.adapter as ImagesAdapter).spanSizeLookup
        binding.rvImages.layoutManager = layoutManager



        viewModel.properties.observe(viewLifecycleOwner, Observer {
            if (it!= null){
                setFragmentResult(REQUEST_KEY, bundleOf(BUNDLE_KEY to it))
            }
        })


        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                this.findNavController()
                    .navigate(GalleryFragmentDirections.actionGalleryFragment2ToDetailFragment2(it))
                viewModel.completeDisplayingProperty()
            }
        })

        binding.btnSearch.setOnClickListener {
            val q = binding.editTv.text.toString().toLowerCase(Locale.getDefault())
            currentPage = 1
            viewModel.updateSearch(q, currentPage)
            lastSearch = q
        }

        binding.rvImages.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (recyclerView.canScrollVertically(1).not()) {
                    if (currentPage in 1..2) {
                        currentPage++
                        viewModel.updateSearch(lastSearch, currentPage)
                    }
                }
            }
        })

        return binding.root
    }

    override fun onPause() {
        super.onPause()
        val editor = preferences.edit()
        editor.putString(appPrefSearch, lastSearch)
        editor.putInt(appPrefPage,currentPage)
        editor.apply()
    }

    override fun onResume() {
        super.onResume()
        if (preferences.contains(appPrefSearch)) {
            lastSearch = preferences.getString(appPrefSearch, baseQ).toString()
            currentPage = preferences.getInt(appPrefPage,1)
            viewModel.updateSearch(lastSearch, currentPage)
        }
    }


}
