package com.example.testnatife

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testnatife.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as ListFragment

        listFragment.setItemClickListener {
            val detailsFragment = DetailsFragment().newInstance(imageUrl = it.images.original.url)

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, detailsFragment)
                .addToBackStack(null)
                .commit()
        }
    }
}
