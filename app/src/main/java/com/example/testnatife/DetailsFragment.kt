package com.example.testnatife

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.testnatife.databinding.DetailsFragmentLayoutsBinding

class DetailsFragment:Fragment() {
    private lateinit var binding: DetailsFragmentLayoutsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailsFragmentLayoutsBinding.inflate(inflater, container, false)

        Glide.with(binding.imageView)
            .load(requireArguments().getString(URL_IMAGE))
            .into(binding.imageView)
        return binding.root
    }

    companion object {
        val URL_IMAGE = "url image"
    }

    fun newInstance(imageUrl:String):DetailsFragment {
        val args = Bundle().apply {
            putString(URL_IMAGE, imageUrl)
        }
        val fragment = DetailsFragment()
        fragment.arguments = args

        return fragment
    }
}
