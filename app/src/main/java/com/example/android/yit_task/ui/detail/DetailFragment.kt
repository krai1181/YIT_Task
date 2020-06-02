package com.example.android.yit_task.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import com.example.android.yit_task.databinding.DetailFragmentBinding
import com.example.android.yit_task.network.HitsData
import com.example.android.yit_task.ui.BUNDLE_KEY
import com.example.android.yit_task.ui.REQUEST_KEY

class DetailFragment : Fragment() {

    private lateinit var binding: DetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application
        binding = DetailFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val hit = DetailFragmentArgs.fromBundle(requireArguments()).selectedProperty
        val viewModelFactory = DetailViewModelFactory(hit, application)
        val viewModel = ViewModelProvider(this, viewModelFactory)[DetailViewModel::class.java]
        binding.viewModel = viewModel
        binding.viewPager.adapter = ImagePagerAdapter(hit)


        //get list<hits> from GalleryFragment
        setFragmentResultListener(REQUEST_KEY) { _, bundle ->
            val hitsList = bundle.getParcelable<HitsData>(BUNDLE_KEY)
            viewModel.updateData(hitsList?.hits)
        }



        binding.ivShare.setOnClickListener {
            val shareIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_STREAM, hit.largeImageURL)
                type = "image/jpg"
            }
            startActivity(Intent.createChooser(shareIntent, "Image"))
        }








        return binding.root
    }


}
