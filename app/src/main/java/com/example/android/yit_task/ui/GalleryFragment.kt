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
    private val baseQ = "kitten"

    private var lastSearch:String = baseQ

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
            viewModel.displayPropertyDetail(it)
        })

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
            viewModel.updateSearch(q)
            lastSearch = q
        }




        return binding.root
    }

    override fun onPause() {
        super.onPause()
        val editor = preferences.edit()
        editor.putString(appPrefSearch, lastSearch)
        editor.apply()
    }

    override fun onResume() {
        super.onResume()
        if (preferences.contains(appPrefSearch)) {
            lastSearch = preferences.getString(appPrefSearch, baseQ).toString()
            viewModel.updateSearch(lastSearch)

        }
    }


}
